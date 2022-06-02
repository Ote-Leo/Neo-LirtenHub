package com.tau.user_profiler.requests;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User_Request {
    private Long user_id;
    private String github_link, interest, preference;

    public User_Request(){
        user_id = null;
        github_link = null;
        interest = null;
        preference = null;
    }
}
