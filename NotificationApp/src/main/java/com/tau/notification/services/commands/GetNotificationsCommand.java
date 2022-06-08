package com.tau.notification.services.commands;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.tau.notification.models.Notification;
import com.tau.notification.repositories.Notification_Custom;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class GetNotificationsCommand extends CommandDP{
    
    private final Notification_Custom notification_custom;

    @Override
    public Object execute(){
        ArrayList<Notification> notifications = notification_custom.getNotifications(((Notification) data).getUser_id());
        return notifications;
    }

}
