package com.tau.user_profiler.services.commands.preferences;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tau.user_profiler.models.UserProfile;
import com.tau.user_profiler.repositories.User_Repository;
import com.tau.user_profiler.requests.User_Request;
import com.tau.user_profiler.services.commands.CommandDP;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetPreferenceCommand extends CommandDP{
    private static final String ERROR = "Opss! Transaction failed."; 

    private final User_Repository user_repository;

    @Override
    public Object execute() {
        Optional<UserProfile> list = user_repository.findById(((User_Request) data).getUser_id());
        
        if(list.isEmpty())
            return ERROR + " USER does not exist.";
        else
            return list.get().getProjectPreferenceTags();
    }
    
}
