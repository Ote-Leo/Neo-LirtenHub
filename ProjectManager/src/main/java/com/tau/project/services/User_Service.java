package com.tau.project.services;

import com.tau.project.models.Users;
import com.tau.project.repositories.User_Repository;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class User_Service {
    private final User_Repository user_repository;

    public void add_user(Users user){
        user_repository.save(user);
    }        
}
