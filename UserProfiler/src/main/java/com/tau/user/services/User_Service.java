package com.tau.user.services;

import com.tau.user.models.UserProfile;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.User_Request;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class User_Service {
    private final User_Repository user_repository;
    
    public void add_user(UserProfile user) {
        user_repository.save(user);
    }

    public void editFirstName(User_Request user_request){
        
    }
    
    public void editLastName(String usrEmail,String name){
        
    }
    public void editPassword(String usrEmail,String Password){
        
    }

}
