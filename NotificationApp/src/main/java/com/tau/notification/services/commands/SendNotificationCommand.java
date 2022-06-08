package com.tau.notification.services.commands;


import org.springframework.stereotype.Service;

import com.tau.notification.models.Notification;
import com.tau.notification.repositories.Notification_Repository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class SendNotificationCommand extends CommandDP{
    
    private final Notification_Repository notification_repository;

    @Override
    public String execute(){
        notification_repository.save(((Notification) data));
        return String.format("ADDED NOTIFICATION %s SUCCESSFULLY", ((Notification) data).toString()); 
        
    }

}
