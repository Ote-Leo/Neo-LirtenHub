package com.tau.user.services.commands.registeration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.tau.user.models.UserAuth;
import com.tau.user.repositories.UserAuth_Repository;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.UserAuth_Request;
import com.tau.user.services.commands.CommandDP;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddLoginCommand extends CommandDP{
    private static final String UPDATED_SUCCESS = "Login successfully!"; 
    private static final String ERROR = "Opss! Login failed."; 
    
    private final UserAuth_Repository userauth_repository;
    private final User_Repository user_repository;
 
    @Override
    public String execute() {
        
        if(user_repository.findById(((UserAuth_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist.";

        UserAuth userauth = new UserAuth();  
        userauth.setId(getNextId());
        userauth.setUser_id(((UserAuth_Request) data).getUser_id());
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("GMT+02:00"));
        userauth.setLoginTime(localDateTime);
        userauth.setLogoutTime(null);
        
        userauth_repository.save(userauth);
    

        
        return UPDATED_SUCCESS;
    }

    public long getNextId(){
        long max = 0;

        ArrayList<UserAuth> list = (ArrayList<UserAuth>) userauth_repository.findAll();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId() > max)
                max = list.get(i).getId();
        }

        return max + 1;
    }
    
}
