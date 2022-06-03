package com.tau.user.services.commands.edit_profile;

import org.springframework.stereotype.Service;

import com.tau.user.repositories.User_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.User_Request;
import com.tau.user.services.commands.CommandDP;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EditFirstNameCommand extends CommandDP{
    private static final String UPDATED_SUCCESS = "updated successfully!"; 
    private static final String ERROR = "Opss! Transaction failed."; 

    private final User_Repository user_repository;
    private final User_Custom user_custom;

    @Override
    public String execute() {
        if(user_repository.findById(((User_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist.";

        
        user_custom.updateFirstName(((User_Request) data).getUser_id(), ((User_Request) data).getFirst_name());
        return "First name " + UPDATED_SUCCESS;


    }
    
}
