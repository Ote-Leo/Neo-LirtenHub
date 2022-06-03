package com.tau.notification.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tau.notification.models.Notification;

@Repository
public interface Notification_Repository extends MongoRepository<Notification, Long> {
    
}
