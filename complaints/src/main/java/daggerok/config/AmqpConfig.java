package daggerok.config;

import lombok.val;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    final static String NAME = "ComplaintEvents";

    @Bean
    public ConnectionFactory connectionFactory(ApplicationContext applicationContext) {

        val cachingConnectionFactory = new CachingConnectionFactory();

        cachingConnectionFactory.setUsername("admin");
        cachingConnectionFactory.setPassword("admin");
        cachingConnectionFactory.setApplicationContext(applicationContext);
        return cachingConnectionFactory;
    }

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
                .to(exchange()).with(NAME)
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
