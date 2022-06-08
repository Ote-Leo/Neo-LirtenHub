package com.tau.project.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Notify_Request {
    private Long accepted_id, user_id;

    public Notify_Request(){
        this.accepted_id = null;
        this.user_id = null;
    }

}
