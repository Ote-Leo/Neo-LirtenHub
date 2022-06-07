package com.tau.user.requests;

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
    private String github_link, interest, coding_language, preference, first_name, last_name, password, email, location;

    public User_Request(){
        user_id = null;
        github_link = null;
        interest = null;
        preference = null;
        first_name = null;
        last_name = null;
        password = null;
        coding_language = null;
        email = null;
        location = null;
    }
}
