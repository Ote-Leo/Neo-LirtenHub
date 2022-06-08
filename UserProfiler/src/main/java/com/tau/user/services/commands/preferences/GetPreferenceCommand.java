package com.tau.user.services.commands.preferences;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tau.user.services.commands.CommandDP;
import com.tau.user.models.UserAuth;
import com.tau.user.models.UserProfile;
import com.tau.user.repositories.UserAuth_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.User_Request;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetPreferenceCommand extends CommandDP{
    private static final String ERROR = "Opss! Transaction failed."; 

    private final User_Repository user_repository;

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
    public Object execute() {
        if(!isLoggedIn(((User_Request) data).getUser_id()))
            return ERROR + " You are not logged in.";  
            
        Optional<UserProfile> list = user_repository.findById(((User_Request) data).getUser_id());
        
        if(list.isEmpty())
            return ERROR + " USER does not exist.";
        else
            return list.get().getProjectPreferenceTags();
    }
    
}
