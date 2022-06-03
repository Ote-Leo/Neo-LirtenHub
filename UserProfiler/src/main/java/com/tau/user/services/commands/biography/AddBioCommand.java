package com.tau.user.services.commands.biography;


import com.tau.user.repositories.User_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.Bio_Request;
import com.tau.user.services.commands.CommandDP;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddBioCommand extends CommandDP {
    private static final String UPDATED_SUCCESS = "Bio updated successfully!"; 
    private static final String ERROR = "Opss! Transaction failed."; 
    
    private final User_Custom user_custom;
    private final User_Repository user_repository;
 
    @Override
    public String execute() {
        
        if(user_repository.findById(((Bio_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist.";

        
        user_custom.updateBio(((Bio_Request) data).getUser_id(), ((Bio_Request) data).getText_section());
        return UPDATED_SUCCESS;
    }
    
}
