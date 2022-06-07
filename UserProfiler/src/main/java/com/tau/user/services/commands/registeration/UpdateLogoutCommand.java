package com.tau.user.services.commands.registeration;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.tau.user.repositories.UserAuth_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.UserAuth_Request;

import com.tau.user.services.commands.CommandDP;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateLogoutCommand extends CommandDP{
    private static final String UPDATED_SUCCESS = "Logout successfully!"; 
    private static final String ERROR = "Opss! Login failed."; 
    
    private final UserAuth_Custom userauth_custom;
    private final User_Repository user_repository;


 
    @Override
    public String execute() {
        
        if(user_repository.findById(((UserAuth_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist.";

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("GMT+02:00"));
        //userauth.setLoginTime(localDateTime);    
        userauth_custom.updatelogoutTime(((UserAuth_Request) data).getUser_id(), localDateTime);
        return UPDATED_SUCCESS;
    }
    
}
