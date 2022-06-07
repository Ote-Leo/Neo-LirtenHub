package com.tau.user.RabbitMQConfiguration;


import java.util.LinkedHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.User_Request;
import com.tau.user.services.commands.location.AddLocationCommand;

@Component
public class Consumer {

    @Autowired
    RabbitTemplate template;

    @Autowired
    private User_Repository user_repository;

    @Autowired
    private AddLocationCommand add_location_command;

    
    @RabbitListener(queues = "user_queue") 
    public void userConsumer(Message message){
        System.out.println("ashraffffff");
        
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

            template.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.PROJECT_ROUTING_KEY, response);      

        }
        else if(message.getMethod().equals("automatic_location")){

            User_Request user_request = new User_Request();

            LinkedHashMap data = (LinkedHashMap) message.getData();

            Long user_id = new Long((Integer) data.get("user_id"));

            user_request.setUser_id(user_id);
            user_request.setLocation((String) data.get("city_name"));
            add_location_command.setData(user_request);
            System.out.println(add_location_command.execute());

        }

    }



}
