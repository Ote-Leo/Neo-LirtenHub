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
    public static final String EXCHANGE = "project_exchange";
    public static final String ROUTING_KEY = "project_routing_key";
    public static final String QUEUE = "project_queue";

    @Bean
    public Queue customerOrderQueue(){
        return new Queue(QUEUE);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingCustomerOrderQueue(TopicExchange exchange){
        return BindingBuilder.bind(customerOrderQueue()).to(exchange).with(ROUTING_KEY);
    }

}
