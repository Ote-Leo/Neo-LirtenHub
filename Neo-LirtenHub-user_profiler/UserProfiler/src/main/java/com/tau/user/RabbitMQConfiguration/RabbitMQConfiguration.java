package com.tau.user.RabbitMQConfiguration;

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
    public static final String PROJECT_EXCHANGE = "project_exchange";
    public static final String PROJECT_ROUTING_KEY = "project_routing_key";
    public static final String PROJECT_QUEUE = "project_queue";

    @Bean
    public Queue userProjectQueue(){
        return new Queue(PROJECT_QUEUE);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(PROJECT_EXCHANGE);
    }

    @Bean
    public Binding bindingUserProjectQueue(TopicExchange exchange){
        return BindingBuilder.bind(userProjectQueue()).to(exchange).with(PROJECT_ROUTING_KEY);
    }

}
