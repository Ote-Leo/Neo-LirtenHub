package com.tau.user.services.commands.interests;

import org.springframework.stereotype.Service;

import com.tau.user.services.commands.CommandDP;
import com.tau.user.repositories.User_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.User_Request;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddInterestCommand extends CommandDP{
    private static final String ADDED_SUCCESS = "added successfully!"; 
    private static final String ERROR = "Opss! Transaction failed."; 

    private final User_Repository user_repository;
    private final User_Custom user_custom;

    @Override
    public String execute() {
        if(user_repository.findById(((User_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist.";
        
        if(user_custom.getInterests(((User_Request) data).getUser_id()) != null && 
        user_custom.getInterests(((User_Request) data).getUser_id()).contains(((User_Request) data).getInterest()))
            return ERROR + " Interest already found!"; 

        user_custom.updateInterest(((User_Request) data).getUser_id(), ((User_Request) data).getInterest(), "add");
        return "Interest " + ADDED_SUCCESS;
    }
}
