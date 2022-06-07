package com.tau.user.services.commands.reporting;

import com.tau.user.models.Report;
import com.tau.user.models.UserAuth;
import com.tau.user.models.UserProfile;
import org.springframework.stereotype.Service;

import com.tau.user.repositories.UserAuth_Custom;
import com.tau.user.repositories.User_Repository;
import com.tau.user.services.commands.CommandDP;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetReportsCommand extends CommandDP{

    private final User_Repository user_repository;

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

    @Async("asyncExecutor") 
    @Override
    public ArrayList<String> execute() {
 

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
