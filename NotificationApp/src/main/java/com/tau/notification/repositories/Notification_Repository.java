package com.tau.notification.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tau.notification.models.Notification;

public interface Notification_Repository extends MongoRepository<Notification, Long> {
    
}
