package com.tau.user.services.commands.coding_languages;


import org.springframework.stereotype.Service;

import com.tau.user.services.commands.CommandDP;
import com.tau.user.repositories.User_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.User_Request;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddCodingLanguagesCommand extends CommandDP{
    private static final String ADDED_SUCCESS = " added successfully!"; 
    private static final String ERROR = "Opss! Transaction failed."; 

    private final User_Repository user_repository;
    private final User_Custom user_custom;

    @Override
    public String execute() {
        if(user_repository.findById(((User_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist.";
        
        if(user_custom.getLanguages(((User_Request) data).getUser_id()) != null && 
        user_custom.getLanguages(((User_Request) data).getUser_id()).contains(((User_Request) data).getCoding_language()))
            return ERROR + " " + ((User_Request) data).getCoding_language() + " already found!"; 

        user_custom.updateLanguage(((User_Request) data).getUser_id(), ((User_Request) data).getCoding_language());
        return ((User_Request) data).getCoding_language() + ADDED_SUCCESS;
    }
}
