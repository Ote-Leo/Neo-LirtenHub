package com.tau.notification.RabbitMQConfiguration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tau.notification.models.Notification;
import com.tau.notification.repositories.Notification_Repository;
import com.tau.notification.requests.Notify_Request;
import com.tau.notification.services.commands.GetNotificationsCommand;
import com.tau.notification.services.commands.SendNotificationCommand;


@Component
public class Consumer {

    @Autowired
    RabbitTemplate template;

    @Autowired
    private GetNotificationsCommand getNotificationsCommand;

    @Autowired
    private SendNotificationCommand sendNotificationsCommand;

    @Autowired
    private Notification_Repository notification_repository;



    @RabbitListener(queues = "notification_queue") 
    public void notificationConsumer(Message message){

        System.out.println("ashrafffff");

        if(message.getMessage().equals("user_not_exist")){
            System.out.println("Output: Opss! Transaction failed. USER does not exist.");;
        }
        else if(message.getMessage().equals("login_fail")){
            System.out.println("Output: Opss! Transaction failed.  You are not logged in.");;
        }
        else if(message.getMessage().equals("get_notification_success")){
            LinkedHashMap data = (LinkedHashMap) message.getData();

            Long user_id = null;

            Integer user_Integer = (Integer) data.get("user_id");
            
            if(user_Integer != null)
                user_id = new Long(user_Integer);

            Notification notification = new Notification();
            notification.setUser_id(user_id);

            getNotificationsCommand.setData(notification);
            ArrayList<Notification> output = (ArrayList<Notification>) getNotificationsCommand.execute();
            System.out.println("Output: " + output);
        
        }else if(message.getMethod().equals("accept_applicant")){
            ArrayList<LinkedHashMap> list = (ArrayList<LinkedHashMap>) message.getData();

            Notification notification;



            for (LinkedHashMap map : list) {
                Long accepted_id = null, user_id = null;

                Integer accepted_Integer = (Integer) map.get("accepted_id"),
                user_Integer = (Integer) map.get("user_id");

                if(accepted_Integer != null)
                    accepted_id = new Long(accepted_Integer);
            
                if(user_Integer != null)
                    user_id = new Long(user_Integer);

                //System.out.println(map.values());
                notification = new Notification();

                notification.setId(getNextProjectID());
                notification.setUser_id(user_id);
                LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("GMT+02:00"));
                notification.setTimestamp(localDateTime);
                notification.setMessage("USER " + accepted_id + " has been assigned to your project.");

                sendNotificationsCommand.setData(notification);
                String output = sendNotificationsCommand.execute();
                System.out.println(output);
            }



        }

        else if(message.getMethod().equals("notify")){
            ArrayList<LinkedHashMap> list = (ArrayList<LinkedHashMap>) message.getData();

            Notification notification;



            for (LinkedHashMap map : list) {
                Long accepted_id = null, user_id = null;

                Integer accepted_Integer = (Integer) map.get("accepted_id"),
                user_Integer = (Integer) map.get("user_id");

                if(accepted_Integer != null)
                    accepted_id = new Long(accepted_Integer);
            
                if(user_Integer != null)
                    user_id = new Long(user_Integer);

                //System.out.println(map.values());
                notification = new Notification();

                notification.setId(getNextProjectID());
                notification.setUser_id(user_id);
                LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("GMT+02:00"));
                notification.setTimestamp(localDateTime);
                notification.setMessage("USER " + accepted_id + " has editted his profile.");

                sendNotificationsCommand.setData(notification);
                String output = sendNotificationsCommand.execute();
                System.out.println(output);
            }



        }

      
        

    }

    public long getNextProjectID(){
        long max = 0;

        ArrayList<Notification> list = (ArrayList<Notification>) notification_repository.findAll();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId() > max)
                max = list.get(i).getId();
        }

        return max + 1;
    }


}
