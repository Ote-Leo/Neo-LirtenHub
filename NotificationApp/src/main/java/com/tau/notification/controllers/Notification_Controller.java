package com.tau.notification.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tau.notification.models.Notification;
import com.tau.notification.repositories.Notification_Repository;
import com.tau.notification.requests.NotificationUpdateRequest;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class Notification_Controller {
    private final Notification_Repository notification_Repository;
    
    @PostMapping("/add")
    public void sendNotification(@RequestBody Notification notification) {
        notification_Repository.save(notification);
    }

    @PostMapping("/read")
    public void readNotification(@RequestBody NotificationUpdateRequest readNotificationRequest) {
        Notification notification = notification_Repository.findById(readNotificationRequest.getNotificationId()).get();
        notification.setRead(true);
        notification_Repository.save(notification);
    }

    @GetMapping("/get/{id}")
    public Notification getNotification(@PathVariable Long id) {
        Optional<Notification> notification = notification_Repository.findById(id);

        if(!notification.isPresent())
            throw new IllegalStateException(String.format("NOTIFICATION WITH ID %d NOT FOUND", id));

        return notification.get();
    }

    @GetMapping("/all")
    public List<Notification> getAllNotifications() {
        return notification_Repository.findAll();
    }
}
