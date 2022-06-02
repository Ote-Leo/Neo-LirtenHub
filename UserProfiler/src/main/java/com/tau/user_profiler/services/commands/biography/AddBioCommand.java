package com.tau.user_profiler.services.commands.biography;


import org.springframework.stereotype.Service;

import com.tau.user_profiler.repositories.User_Custom;
import com.tau.user_profiler.repositories.User_Repository;
import com.tau.user_profiler.requests.Bio_Request;
import com.tau.user_profiler.services.commands.CommandDP;

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
