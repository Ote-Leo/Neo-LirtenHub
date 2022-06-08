package com.tau.user.services.commands.location;

import java.util.ArrayList;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.user.RabbitMQConfiguration.Message;
import com.tau.user.RabbitMQConfiguration.RabbitMQConfiguration;
import com.tau.user.models.UserAuth;
import com.tau.user.repositories.UserAuth_Custom;
import com.tau.user.repositories.User_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.Notify_Request;
import com.tau.user.requests.User_Request;
import com.tau.user.services.commands.CommandDP;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddLocationCommand extends CommandDP{
    private static final String ADDED_SUCCESS = "Location added successfully!"; 
    private static final String ERROR = "Opss! Transaction failed."; 

    private final User_Repository user_repository;
    private final User_Custom user_custom;

    private final UserAuth_Custom userauth_custom;
 
    @Autowired
    RabbitTemplate template;

    public boolean isLoggedIn(Long user_id){
        boolean flag = false;

        ArrayList<UserAuth> list = userauth_custom.getUsers(user_id);

        for(UserAuth userAuth : list){
            if(userAuth.getLogoutTime() == null){
                flag = true;
                break;
            }
        }

        return flag;
    }

    @Override
    public String execute() {
        if(user_repository.findById(((User_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist.";
        
        if(!isLoggedIn(((User_Request) data).getUser_id()))
            return ERROR + " You are not logged in.";  

        Notify_Request notify_request = new Notify_Request();
        notify_request.setAccepted_id(((User_Request) data).getUser_id());

        Message message = new Message();
        message.setMethod("accept_applicant");
        message.setMessage("accept_applicant");
        message.setData(notify_request);

        template.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.PROJECT_ROUTING_KEY, message);      
                

        user_custom.updateLocation(((User_Request) data).getUser_id(), ((User_Request) data).getLocation());
        return ADDED_SUCCESS;
    }
    
}