package com.tau.user.services.commands.reporting;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.tau.user.models.Report;
import com.tau.user.models.UserAuth;
import com.tau.user.repositories.UserAuth_Custom;
import com.tau.user.repositories.User_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.requests.Report_Request;
import com.tau.user.services.commands.CommandDP;
import com.tau.user.requests.User_Request;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReportCommand extends CommandDP{
    private static final String ERROR = "Opss! Transaction failed."; 
    private static final String ADDED_SUCCESS = "Reported successfully!"; 

    private final User_Repository user_repository;
    private final User_Custom user_custom;
    private final UserAuth_Custom userauth_custom;
    

    public boolean isLoggedIn(Long user_id){
        boolean flag = false;

        ArrayList<UserAuth> list = userauth_custom.getUsers(user_id);

        for(UserAuth userAuth : list){
            if(userAuth.getLogoutTime() == null){
                flag = true;
                break;
            }
        }

        return flag;
    }
    
    @Override
    public String execute() {
        if(user_repository.findById(((Report_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist";
        
        if(user_repository.findById(((Report_Request) data).getReported_id()).isEmpty())
            return ERROR + " REPORTED USER does not exist";

        if(!isLoggedIn(((User_Request) data).getUser_id()))
            return ERROR + " You are not logged in.";
            

        if(user_custom.getReport(((Report_Request) data).getUser_id()) == null){
            user_custom.updateReport(((Report_Request) data).getUser_id(), ((Report_Request) data).getReported_id(), ((Report_Request) data).getReport_description());
            return ADDED_SUCCESS;
        }else{
            boolean flag = false;

            for (Report report : user_custom.getReport(((Report_Request) data).getUser_id())) {
                if(report.getReported_id() == ((Report_Request) data).getReported_id()){
                    flag = true;
                    break;
                }
            }

            if(!flag){
                user_custom.updateReport(((Report_Request) data).getUser_id(), ((Report_Request) data).getReported_id(), ((Report_Request) data).getReport_description());
                return ADDED_SUCCESS;
            }else
                return ERROR + " Already reported!";


        }
    }
    
}
