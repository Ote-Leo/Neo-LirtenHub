package com.tau.notification.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Notify_Request {
    private final Long accepted_id, user_id;

    public Notify_Request(){
        this.accepted_id = null;
        this.user_id = null;
    }

}
