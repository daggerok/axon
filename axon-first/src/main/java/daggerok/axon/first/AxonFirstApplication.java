package daggerok.axon.first;

import daggerok.axon.first.domain.DomainQueryObject;
import daggerok.axon.first.domain.DomainQueryObjectRepository;
import daggerok.axon.first.event.DomainEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class AxonFirstApplication {

    @RestController
    @RequiredArgsConstructor
    public static class DomainApi {

        final CommandGateway commandGateway;
        final DomainQueryObjectRepository repository;

        @PostMapping
        public CompletableFuture<String> save(@RequestBody Map<String, String> request) {
            val id = UUID.randomUUID().toString();
            return commandGateway.send(new DomainCommandObject(id, request.get("company"), request.get("description")));
        }

        @GetMapping
        public List<DomainQueryObject> findAll() {
            return repository.findAll();
        }

        @GetMapping("/{id}")
        public DomainQueryObject findOne(@PathVariable final String id) {
            return repository.findOne(id);
        }
    }

    @Data
    @Aggregate
    @NoArgsConstructor
    public static class Domain {

        @AggregateIdentifier
        private String id;

        @CommandHandler
        public Domain(DomainCommandObject cmd) {

            Assert.hasLength(cmd.getCompany());

            AggregateLifecycle.apply(new DomainEvent(cmd.getId(), cmd.getCompany(), cmd.getDescription()));
        }

        @EventSourcingHandler
        public void on(DomainEvent event) {
            id = event.getId();
        }
    }

    @Component
    @RequiredArgsConstructor
    public static class DomainQueryObjectUpdater {

        final DomainQueryObjectRepository repository;

        @EventHandler
        public void on(DomainEvent event) {
            repository.save(DomainQueryObject.of(event.getId(), event.getCompany(), event.getDescription()));
        }
    }

    @Data
    @RequiredArgsConstructor(staticName = "of")
    public static class DomainCommandObject {

        final String id, company, description;
    }

    public static void main(String[] args) {
        SpringApplication.run(AxonFirstApplication.class, args);
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange("DomainEvents").build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("DomainEvents").build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
    }

    @Autowired
    public void amqpAdminConfig(AmqpAdmin admin) {
        admin.declareExchange(exchange());
        admin.declareQueue(queue());
        admin.declareBinding(binding());
    }
}
