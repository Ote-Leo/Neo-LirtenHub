package com.tau.user.RabbitMQConfiguration;

import com.tau.user.requests.Project_Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String method, message;
    private long owner_id;
    private Project_Request project_request;
}
