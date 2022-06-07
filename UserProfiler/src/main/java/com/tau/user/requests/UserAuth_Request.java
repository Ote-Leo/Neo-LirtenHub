package com.tau.user.requests;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserAuth_Request {
    private final Long id;
    private Long user_id;
    private final LocalDateTime loginTime;
    private final LocalDateTime logoutTime;

  
    public UserAuth_Request(){
        this.id=null;
        this.user_id = null;
        this.loginTime = null;
        this.logoutTime=null;
    }

    // public void setUserId(Long user_id) {
    //     this.user_id = user_id;
    // }

}
