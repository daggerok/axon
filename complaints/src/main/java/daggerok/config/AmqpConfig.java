package daggerok.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    final static String NAME = "ComplaintEvents";

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(NAME).build();
    }

    @Bean
    public Exchange exchange() {

        return ExchangeBuilder
                .topicExchange(NAME)
                .build();
    }

    @Bean
    public Binding binding() {

        return BindingBuilder
                .bind(queue())
//                .to(exchange()).with(NAME)
                .to(exchange()).with("*")
                .noargs();
    }

    @Autowired
    public void config(AmqpAdmin admin) {
        // order is matter
        admin.declareQueue(queue());
        admin.declareExchange(exchange());
        admin.declareBinding(binding());
    }
}
