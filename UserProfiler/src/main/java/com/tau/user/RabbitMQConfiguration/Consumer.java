package com.tau.user.RabbitMQConfiguration;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.Project_Request;

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

            if(user_repository.findById(message.getOwner_id()).isEmpty()){
                response.setMessage("create_project_fail");
            }
            else{
                response.setMessage("create_project_success");
            }

            Project_Request project_Request = (Project_Request) message.getProject_request();

            response.setProject_request(project_Request);

            template.convertAndSend(RabbitMQConfiguration.PROJECT_EXCHANGE, RabbitMQConfiguration.PROJECT_ROUTING_KEY, response);      

        }

    }



}
