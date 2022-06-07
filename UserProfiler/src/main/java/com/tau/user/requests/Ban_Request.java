package com.tau.user.requests;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
public class Ban_Request {
    private Long user_id, banned_id;

    public Ban_Request(){
        this.user_id = null;
        this.banned_id = null;
    }
}
