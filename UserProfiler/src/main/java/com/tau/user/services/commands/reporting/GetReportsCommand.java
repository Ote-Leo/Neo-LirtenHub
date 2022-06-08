package com.tau.user.services.commands.reporting;

import com.tau.user.models.Report;
import com.tau.user.models.UserAuth;
import com.tau.user.models.UserProfile;
import org.springframework.stereotype.Service;

import com.tau.user.repositories.UserAuth_Custom;
import com.tau.user.repositories.User_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.services.commands.CommandDP;
import com.tau.user.requests.User_Request;


import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetReportsCommand extends CommandDP{

    private final User_Repository user_repository;
    private final User_Custom user_custom;

    private final UserAuth_Custom userauth_custom;

    private static final String ERROR = "Opss! Transaction failed. "; 
 

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
    public Object execute() {
        if(user_repository.findById(((User_Request) data).getUser_id()).isEmpty())
            return ERROR + "USER does not exist.";

        if(!isLoggedIn(((User_Request) data).getUser_id()))
            return ERROR + " You are not logged in.";

        if(!user_custom.isModerator(((User_Request) data).getUser_id()))
            return ERROR + " You are not a moderator!";

        
        ArrayList<UserProfile> users = (ArrayList<UserProfile>) user_repository.findAll();
        ArrayList<String> reports = new ArrayList<>();

        for (UserProfile user : users) {
            ArrayList<Report> userReports = user.getReported_users();

            if(userReports != null){
                for (Report report : userReports) {
                    Optional<UserProfile> reportedUserOptional = user_repository.findById(report.getReported_id());
                    UserProfile reportedUser = reportedUserOptional.get();
                    String currentReport = "User " + user.getFirstName() + " " + user.getLastName() + " reported " + reportedUser.getFirstName()
                            + " " + reportedUser.getLastName() + " for " +
                            report.getReport_description();
                    reports.add(currentReport);
                }
            }
        }
        return reports;
    }
}
