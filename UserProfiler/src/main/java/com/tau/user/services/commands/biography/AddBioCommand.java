package com.tau.user.services.commands.biography;


import com.tau.user.models.UserAuth;
import com.tau.user.repositories.UserAuth_Custom;
import com.tau.user.repositories.User_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.Bio_Request;
import com.tau.user.services.commands.CommandDP;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddBioCommand extends CommandDP {
    private static final String UPDATED_SUCCESS = "Bio updated successfully!"; 
    private static final String ERROR = "Opss! Transaction failed."; 
    
    private final User_Custom user_custom;
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
 
    @Override
    public String execute() {
        //if user is logged in

        if(user_repository.findById(((Bio_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist.";

        if(!isLoggedIn(((Bio_Request) data).getUser_id()))
            return ERROR + " You are not logged in.";   
        
        user_custom.updateBio(((Bio_Request) data).getUser_id(), ((Bio_Request) data).getText_section());
        return UPDATED_SUCCESS;
    }
    
}
