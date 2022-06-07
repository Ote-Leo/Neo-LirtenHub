package com.tau.user.services.commands.registeration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.tau.user.models.UserAuth;
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

    public boolean isLoggedIn(Long user_id){
        
        boolean flag = false;

        ArrayList<UserAuth> list = userauth_custom.getUsers(user_id);

        for(UserAuth userAuth : list){
            if(userAuth.getLogoutTime() == null){
                flag = true;
                break;
            }
        }

        return flag;
    }

    @Async("asyncExecutor") 
    @Override
    public String execute() {
        
        if(user_repository.findById(((UserAuth_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist.";
        // if user is already logged out
        if(!isLoggedIn(((UserAuth_Request) data).getUser_id()))
            return ERROR + " USER is already logged out.";

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("GMT+02:00"));
        //userauth.setLoginTime(localDateTime);    
        userauth_custom.updatelogoutTime(((UserAuth_Request) data).getUser_id(), localDateTime);
        return UPDATED_SUCCESS;
    }
    
}
