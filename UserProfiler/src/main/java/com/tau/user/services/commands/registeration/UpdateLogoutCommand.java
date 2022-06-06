package com.tau.user.services.commands.registeration;

import org.jvnet.hk2.annotations.Service;

import com.tau.user.repositories.UserAuth_Custom;
import com.tau.user.repositories.UserAuth_Repository;
import com.tau.user.requests.UserAuth_Request;
import com.tau.user.services.commands.CommandDP;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateLogoutCommand extends CommandDP{
    private static final String UPDATED_SUCCESS = "Logout successfully!"; 
    private static final String ERROR = "Opss! Login failed."; 
    
    private final UserAuth_Custom userauth_custom;
    private final UserAuth_Repository userauth_repository;
 
    @Override
    public String execute() {
        
        if(userauth_repository.findById(((UserAuth_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist.";

        userauth_custom.updatelogoutTime(((UserAuth_Request) data).getUser_id(), ((UserAuth_Request) data).getLogoutTime());
        return UPDATED_SUCCESS;
    }
    
}
