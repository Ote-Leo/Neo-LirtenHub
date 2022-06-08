package com.tau.notification.RabbitMQConfiguration;

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
    public static final String EXCHANGE = "exchange";
    public static final String USER_QUEUE = "user_queue";
    public static final String NOTIFICATION_QUEUE = "notification_queue";

    @Bean
    public Queue userNotificationQueue(){
        return new Queue(NOTIFICATION_QUEUE);
    }

    @Bean
    public Queue notificationUserQueue(){
        return new Queue(USER_QUEUE);
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
    public Binding bindingNotificationUserQueue(TopicExchange exchange){
        return BindingBuilder.bind(notificationUserQueue()).to(exchange).with(USER_ROUTING_KEY);
    }

}