package com.tau.notification.models;


import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Document(collection = "notifications")
public class Notification {
    @Id
    private long id, userId;
    private Date timestamp;
    private String title, message, userEmail;
    private boolean read;    
}