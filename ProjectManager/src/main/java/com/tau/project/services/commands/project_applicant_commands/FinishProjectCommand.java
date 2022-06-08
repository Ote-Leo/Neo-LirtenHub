package com.tau.project.services.commands.project_applicant_commands;

import java.util.ArrayList;

import com.tau.project.models.Project_Applicant;
import com.tau.project.repositories.User_Repository;
import com.tau.project.repositories.project.Project_Repository;
import com.tau.project.repositories.project_applicant.Applicant_Custom;
import com.tau.project.repositories.project_applicant.Project_Applicant_Repository;
import com.tau.project.services.commands.CommandDP;
import com.tau.project.requests.Apply_Request;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FinishProjectCommand extends CommandDP {
    private static final String ERROR = "Opss! Transaction failed."; 
    private static final String ACCEPT_SUCCESS = "Project Applicant finished successfully!"; 

    private final Project_Applicant_Repository project_applicant_repository;
    private final Applicant_Custom applicant_custom;
    private final Project_Repository project_repository;
    private final User_Repository user_repository;


    @Override
    public String execute() {
        // if (user_repository.findById(((Apply_Request) data).getUser_id()).isEmpty())
        //     return ERROR + " USER does not exist";
        if (project_repository.findById(((Apply_Request) data).getProject_id()).isEmpty())
            return ERROR + " PROJECT does not exist.";
        
        ArrayList<Project_Applicant> list = (ArrayList<Project_Applicant>) project_applicant_repository.findAll();
        boolean flag = false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().getProject_id().equals(((Apply_Request) data).getProject_id()) &&
                    list.get(i).getId().getUser_id().equals(((Apply_Request) data).getUser_id())
                    && list.get(i).isAccepted() ) {
                flag = true;
                break;
            }

        }

        if (flag) {
            applicant_custom.finish_project(((Apply_Request) data).getUser_id(), ((Apply_Request) data).getProject_id());
            return ACCEPT_SUCCESS;
        } else
            return ERROR + " There is no such project applicant.";
    }

}
