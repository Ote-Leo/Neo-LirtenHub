package com.tau.notification.repositories;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.tau.notification.models.Notification;

@Component
public class Notification_CustomImp implements Notification_Custom {

    @Autowired
    MongoTemplate mongoTemplate;
    
    @Override
    public void updateReadStatus(Long notificationId) {
        Query query = new Query(Criteria.where("id").is(notificationId));
        Update update = new Update();
        update.set("read", true);
        mongoTemplate.updateFirst(query, update, Notification.class);
        
        
    }

    @Override
    public ArrayList<Notification> getNotifications(Long user_id) {
        Query query = new Query(Criteria.where("userId").is(user_id));

        return (ArrayList<Notification>) mongoTemplate.find(query, Notification.class);
    }

}
    

