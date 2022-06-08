package com.tau.user.RabbitMQConfiguration;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tau.user.models.UserAuth;
import com.tau.user.repositories.UserAuth_Custom;
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

    @Autowired
    private UserAuth_Custom userauth_custom;
 

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

    
    @RabbitListener(queues = "user_queue") 
    public void userConsumer(Message message){
        //System.out.println(message.getMethod());

        if(message.getMethod().equals("create_project")){
            
            Message response = new Message();

            Long owner_id = null;
            Integer owner_Integer = (Integer) ((LinkedHashMap) message.getData()).get("owner_id");

            if(owner_Integer != null)
                owner_id = new Long(owner_Integer);

            //System.out.println(owner_id);

            if(user_repository.findById(owner_id).isEmpty()){
                response.setMessage("owner_not_exist");
            }
            else if(!isLoggedIn(owner_id)){
                response.setMessage("login_fail");
            }
            else{
                response.setMessage("create_project_success");
            }

            //System.out.println(response.getMessage());

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

        }else if(message.getMethod().equals("add_applicant")){
            Message response = new Message();

            Long user_id = null;
            Integer user_Integer = (Integer) ((LinkedHashMap) message.getData()).get("user_id");

            if(user_Integer != null)
                user_id = new Long(user_Integer);

            if(user_repository.findById(user_id).isEmpty()){
                response.setMessage("user_not_exist");
            }
            else if(!isLoggedIn(user_id)){
                response.setMessage("login_fail");
            }
            else{
                response.setMessage("add_applicant_success");
            }

            response.setData(message.getData());

            template.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.PROJECT_ROUTING_KEY, response);      


        }else if(message.getMethod().equals("remove_applicant")){
            Message response = new Message();

            Long user_id = null;
            Integer user_Integer = (Integer) ((LinkedHashMap) message.getData()).get("user_id");

            if(user_Integer != null)
                user_id = new Long(user_Integer);

            if(user_repository.findById(user_id).isEmpty()){
                response.setMessage("user_not_exist");
            }
            else if(!isLoggedIn(user_id)){
                response.setMessage("login_fail");
            }
            else{
                response.setMessage("remove_applicant_success");
            }

            response.setData(message.getData());

            template.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.PROJECT_ROUTING_KEY, response);      


        }else if(message.getMethod().equals("accept_applicant")){
            Message response = new Message();

            Long user_id = null;
            Integer user_Integer = (Integer) ((LinkedHashMap) message.getData()).get("user_id");

            if(user_Integer != null)
                user_id = new Long(user_Integer);

            Long owner_id = null;
            Integer owner_Integer = (Integer) ((LinkedHashMap) message.getData()).get("owner_id");

            if(owner_Integer != null)
                owner_id = new Long(owner_Integer);    

            if(user_repository.findById(user_id).isEmpty()){
                response.setMessage("user_not_exist");
            }
            else if(user_repository.findById(owner_id).isEmpty()){
                response.setMessage("owner_not_exist");
            }
            else if(!isLoggedIn(owner_id)){
                response.setMessage("login_fail");
            }
            else{
                response.setMessage("accept_applicant_success");
            }

            response.setData(message.getData());

            template.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.PROJECT_ROUTING_KEY, response);      


        }else if(message.getMethod().equals("finish_project")){
            Message response = new Message();

            Long user_id = null;
            Integer user_Integer = (Integer) ((LinkedHashMap) message.getData()).get("user_id");

            if(user_Integer != null)
                user_id = new Long(user_Integer);

            if(user_repository.findById(user_id).isEmpty()){
                response.setMessage("user_not_exist");
            }
            else if(!isLoggedIn(user_id)){
                response.setMessage("login_fail");
            }
            else{
                response.setMessage("finish_project_success");
            }

            response.setData(message.getData());

            template.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.PROJECT_ROUTING_KEY, response);      

        
        }else if(message.getMethod().equals("pay_user")){
            Message response = new Message();
        
            Long user_id = null;
            Integer user_Integer = (Integer) ((LinkedHashMap) message.getData()).get("user_id");

            if(user_Integer != null)
                user_id = new Long(user_Integer);

            Long owner_id = null;
            Integer owner_Integer = (Integer) ((LinkedHashMap) message.getData()).get("owner_id");

            if(owner_Integer != null)
                owner_id = new Long(owner_Integer);    

            if(user_repository.findById(user_id).isEmpty()){
                response.setMessage("user_not_exist");
            }
            else if(user_repository.findById(owner_id).isEmpty()){
                response.setMessage("owner_not_exist");
            }
            else if(!isLoggedIn(owner_id)){
                response.setMessage("login_fail");
            }
            else{
                response.setMessage("pay_user_success");
            }

            response.setData(message.getData());

            template.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.PROJECT_ROUTING_KEY, response);      

        }else if(message.getMethod().equals("get_notifications")){
            Message response = new Message();
        
            Long user_id = null;
            Integer user_Integer = (Integer) ((LinkedHashMap) message.getData()).get("user_id");

            if(user_Integer != null)
                user_id = new Long(user_Integer);


            if(user_repository.findById(user_id).isEmpty()){
                response.setMessage("user_not_exist");
            }
            else if(!isLoggedIn(user_id)){
                response.setMessage("login_fail");
            }
            else{
                response.setMessage("get_notification_success");
            }

            response.setData(message.getData());

            template.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.NOTIFICATION_ROUTING_KEY, response);      

        }

    }



}
