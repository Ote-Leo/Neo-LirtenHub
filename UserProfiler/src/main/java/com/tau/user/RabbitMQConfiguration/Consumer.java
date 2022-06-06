package com.tau.user.RabbitMQConfiguration;


import java.util.LinkedHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tau.user.repositories.User_Repository;

@Component
public class Consumer {

    @Autowired
    RabbitTemplate template;

    @Autowired
    private User_Repository user_repository;
    
    @RabbitListener(queues = "user_queue") 
    public void userConsumer(Message message){
        
        if(message.getMethod().equals("create_project")){
            
            Message response = new Message();

            Long owner_id = null;
            Integer owner_Integer = (Integer) ((LinkedHashMap) message.getData()).get("owner_id");

            if(owner_Integer != null)
                owner_id = new Long(owner_Integer);

            if(user_repository.findById(owner_id).isEmpty()){
                response.setMessage("create_project_fail");
            }
            else{
                response.setMessage("create_project_success");
            }

            response.setData(message.getData());

            template.convertAndSend(RabbitMQConfiguration.PROJECT_EXCHANGE, RabbitMQConfiguration.PROJECT_ROUTING_KEY, response);      

        }

    }



}
