package com.tau.user.services.commands.preferences;

import java.util.ArrayList;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tau.user.services.commands.CommandDP;
import com.tau.user.models.UserAuth;
import com.tau.user.repositories.UserAuth_Custom;
import com.tau.user.repositories.User_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.User_Request;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeletePreferenceCommand extends CommandDP{
    private static final String DELETE_SUCCESS = " deleted successfully!"; 
    private static final String ERROR = "Opss! Transaction failed."; 

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

    @Async("asyncExecutor")
    @Override
    public String execute() {
        if(user_repository.findById(((User_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist.";
        
        if(!isLoggedIn(((User_Request) data).getUser_id()))
            return ERROR + " You are not logged in.";  
            
        user_custom.updatePreference(((User_Request) data).getUser_id(), ((User_Request) data).getPreference(), "delete");
        return ((User_Request) data).getPreference() + DELETE_SUCCESS;
    }
    
}
