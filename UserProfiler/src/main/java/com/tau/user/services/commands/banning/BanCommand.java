package com.tau.user.services.commands.banning;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.tau.user.models.UserAuth;
import com.tau.user.repositories.UserAuth_Custom;
import com.tau.user.repositories.User_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.Ban_Request;
import com.tau.user.services.commands.CommandDP;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BanCommand extends CommandDP{
    private static final String ERROR = "Opss! Transaction failed."; 
    private static final String BANNED_SUCCESS = "User banned successfully!"; 

    private final User_Repository user_repository;
    private final User_Custom user_custom;

    private final UserAuth_Custom userauth_custom;
 

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
    
    @Override
    public String execute() {
        if(user_repository.findById(((Ban_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist";
        
        if(user_repository.findById(((Ban_Request) data).getBanned_id()).isEmpty())
            return ERROR + " BANNED USER does not exist";  

        if(!isLoggedIn(((Ban_Request) data).getUser_id()))
            return ERROR + " You are not logged in.";  

        if(!user_custom.isModerator(((Ban_Request) data).getUser_id()))
            return ERROR + " You are not a moderator!";

        // remove user from user_repository
        user_repository.deleteById(((Ban_Request) data).getBanned_id());
        return BANNED_SUCCESS;
        
    }
    
}
