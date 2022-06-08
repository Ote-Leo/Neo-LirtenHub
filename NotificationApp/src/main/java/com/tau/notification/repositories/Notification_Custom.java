package com.tau.notification.repositories;

import java.util.ArrayList;

import com.tau.notification.models.Notification;

public interface Notification_Custom {
    public void updateReadStatus(Long notificationId);
    public ArrayList<Notification> getNotifications(Long user_id);

}
