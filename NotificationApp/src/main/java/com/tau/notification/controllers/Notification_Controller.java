package com.tau.notification.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tau.notification.RabbitMQConfiguration.Message;
import com.tau.notification.RabbitMQConfiguration.RabbitMQConfiguration;
import com.tau.notification.models.Notification;
// import com.tau.notification.services.Notification_Service;
import com.tau.notification.services.commands.GetNotificationsCommand;

import java.util.ArrayList;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class Notification_Controller {

    private final GetNotificationsCommand getNotificationsCommand;

    @Autowired
    RabbitTemplate template;


    @GetMapping("/get/{user_id}")
    @Cacheable(value = "notifications",key = "#user_id")
    public void getAllNotifications(@PathVariable Long user_id) {
        Notification notification = new Notification();
        notification.setUser_id(user_id);

        Message message = new Message();
        message.setMethod("get_notifications");
        message.setData(notification);

        template.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.USER_ROUTING_KEY, message);      
  
    }

    // @GetMapping("/get/{user_id}")
    // @Cacheable(value = "notifications",key = "#user_id")
    // public ArrayList<Notification> getAllNotifications(@PathVariable Long user_id) {
        // Notification notification = new Notification();
        // notification.setUser_id(user_id);
    //     getNotificationsCommand.setData(notification);
    //     return (ArrayList<Notification>) getNotificationsCommand.execute();
    // }
}
