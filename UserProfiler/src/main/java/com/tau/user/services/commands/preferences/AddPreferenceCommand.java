package com.tau.user.services.commands.preferences;

import org.springframework.stereotype.Service;

import com.tau.user.services.commands.CommandDP;
import com.tau.user.repositories.User_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.User_Request;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddPreferenceCommand extends CommandDP{
    private static final String ADDED_SUCCESS = " added successfully!"; 
    private static final String ERROR = "Opss! Transaction failed. "; 

    private final User_Repository user_repository;
    private final User_Custom user_custom;

    @Override
    public String execute() {

        if(user_repository.findById(((User_Request) data).getUser_id()).isEmpty())
            return ERROR + "USER does not exist.";

        if(user_custom.getPreferences(((User_Request) data).getUser_id()) != null && 
        user_custom.getPreferences(((User_Request) data).getUser_id()).contains(((User_Request) data).getPreference()))
            return ERROR + ((User_Request) data).getPreference() + " already found!"; 

        user_custom.updatePreference(((User_Request) data).getUser_id(), ((User_Request) data).getPreference(), "add");
        return ((User_Request) data).getPreference() + ADDED_SUCCESS;
    }
    
}