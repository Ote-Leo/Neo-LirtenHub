package com.tau.project.services.Configeration;
import org.springframework.amqp.core.Queue;

import java.io.Serializable;

import org.apache.logging.log4j.message.Message;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2XmlMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;

public class ProjectRabbitMQConfig implements Serializable {
    public static final String Exchange="project_exchange";
    public static final String Routing_KEYS="project_routing_key";
    public static final String Queue="project_queue";

    @Bean
    public Queue projectQueue(){
        return new Queue(Queue);
    }
    
    @Bean
    public MessageConverter converter()
    {
        return new Jackson2XmlMessageConverter();
    }
    @Bean
    public TopicExchange exchange(){
         return new TopicExchange(Exchange);
    }
    
    @Bean
    public Binding bindingprojectServiceQueue(TopicExchange exchange){
        return BindingBuilder.bind(projectQueue()).to(exchange).with(Routing_KEYS);
    }
}
