package com.tau.user.services.commands.blocking;

import org.springframework.stereotype.Service;

import com.tau.user.repositories.User_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.Block_Request;
import com.tau.user.services.commands.CommandDP;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlockCommand extends CommandDP{
    private static final String ERROR = "Opss! Transaction failed."; 
    private static final String ADDED_SUCCESS = "Blocked successfully!"; 

    private final User_Repository user_repository;
    private final User_Custom user_custom;
    
    @Override
    public String execute() {
        if(user_repository.findById(((Block_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist";
        
        if(user_repository.findById(((Block_Request) data).getBlocked_id()).isEmpty())
            return ERROR + " BLOCKED USER does not exist";  

        if(user_custom.getBlock(((Block_Request) data).getUser_id()) == null || 
        !user_custom.getBlock(((Block_Request) data).getUser_id()).contains(((Block_Request) data).getBlocked_id())){
            user_custom.updateBlock(((Block_Request) data).getUser_id(), ((Block_Request) data).getBlocked_id());
            return ADDED_SUCCESS;
        }   
        else
            return ERROR + " Already blocked!";
    }
    
}
