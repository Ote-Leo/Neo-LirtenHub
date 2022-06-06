package com.tau.project.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Apply_Request {
    private final Long project_id, user_id;

    public Apply_Request(){
        this.project_id = null;
        this.user_id = null;
    }
}
