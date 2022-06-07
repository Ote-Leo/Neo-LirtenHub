package com.tau.user.services.commands.registeration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.tau.user.models.UserProfile;
import com.tau.user.repositories.User_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.User_Request;
import com.tau.user.services.commands.CommandDP;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegisterCommand extends CommandDP{
    private static final String UPDATED_SUCCESS = "Registered successfully!"; 
    
    private final User_Repository user_repository;
    private final User_Custom user_custom;
 

    @Override
    public String execute() throws NoSuchAlgorithmException {
        
        // check if the email is already registered
        if(!user_custom.checkUniqueEmail(((User_Request)  data).getEmail())){
            return "Email already registered!";
        }

        UserProfile userProfile = new UserProfile();

        userProfile.setId(getNextId());
        userProfile.setFirstName(((User_Request)  data).getFirst_name());
        userProfile.setLastName(((User_Request)  data).getLast_name());
        userProfile.setEmail(((User_Request)  data).getEmail());
        userProfile.setUserRole(((User_Request)  data).getUserRole());

        MessageDigest message_digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = message_digest.digest(((User_Request)  data).getPassword().getBytes());
        String passwordHash = new String(hashBytes);

        userProfile.setPassword(passwordHash);

        user_repository.save(userProfile);

        return UPDATED_SUCCESS;
    }

    public long getNextId(){
        long max = 0;

        ArrayList<UserProfile> list = (ArrayList<UserProfile>) user_repository.findAll();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId() > max)
                max = list.get(i).getId();
        }

        return max + 1;
    }
    
}
