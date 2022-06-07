package com.tau.notification.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tau.notification.models.Notification;
import com.tau.notification.repositories.Notification_Custom;
import com.tau.notification.repositories.Notification_Repository;
import com.tau.notification.requests.NotificationUpdateRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Notification_Service {
    private final Notification_Repository notification_Repository;
    private final Notification_Custom notification_Custom;


    public String addNotification(Notification notification) {
        notification_Repository.save(notification);
        return String.format("ADDED NOTIFICATION %s SUCCESSFULLY", notification.toString()); 
    }

    public Notification getNotification(NotificationUpdateRequest nur) {
        Optional<Notification> notification = notification_Repository.findById(nur.getNotificationId());

        if(!notification.isPresent()) {
            throw new IllegalStateException(String.format("NOTIFICATION WITH ID %s NOT FOUND", nur.getNotificationId()));
        }

        return notification.get();
    }

    public List<Notification> getAllNotifications() {
        return notification_Repository.findAll();
    }

    public String deleteNotification(NotificationUpdateRequest nur) {
        Long id = nur.getNotificationId();
        notification_Repository.deleteById(id);
        return String.format("DELETED NOTIFICATION %s SUCCESSFULLY", id);
    }

    public String readNotification(NotificationUpdateRequest readNotificationRequest) {
        Notification notification = notification_Repository.findById(readNotificationRequest.getNotificationId()).get();
        notification.setRead(true);
        notification_Repository.save(notification);
        return String.format("READ NOTIFICATION %s SUCCESSFULLY", notification.toString());
    }

    public String updateReadStatus(Long notificationId) {
        notification_Custom.updateReadStatus(notificationId);
        return "READ STATUS UPDATED";
    }
}