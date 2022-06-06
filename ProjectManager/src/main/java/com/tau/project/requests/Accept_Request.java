package com.tau.project.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Accept_Request {
    private final Long owner_id, project_id, user_id;

    public Accept_Request(){
        this.owner_id = null;
        this.project_id = null;
        this.user_id = null;
    }

}
