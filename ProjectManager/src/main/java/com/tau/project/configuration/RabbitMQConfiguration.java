package com.tau.project.configuration;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    public static final String USER_ROUTING_KEY = "user_routing_key";
    public static final String USER_EXCHANGE = "user_exchange";
    public static final String USER_QUEUE = "user_queue";

    public static final String PROJECT_QUEUE = "project_queue";

    @Bean
    public Queue userProjectQueue(){
        return new Queue(PROJECT_QUEUE);
    }

    @Bean
    public Queue projectUserQueue(){
        return new Queue(USER_QUEUE);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(USER_EXCHANGE);
    }

    @Bean
    public Binding bindingCustomerOrderQueue(TopicExchange exchange){
        return BindingBuilder.bind(projectUserQueue()).to(exchange).with(USER_ROUTING_KEY);
    }

}
