package com.tau.project.services.commands.project_applicant_commands;

import com.tau.project.models.Project_Applicant;
import com.tau.project.models.Project_Applicant.CompositeKey;
import com.tau.project.repositories.User_Repository;
import com.tau.project.repositories.project.Project_Repository;
import com.tau.project.repositories.project_applicant.Project_Applicant_Repository;
import com.tau.project.requests.Apply_Request;
import com.tau.project.services.commands.CommandDP;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ApplyApplicantCommand extends CommandDP{
    private static final String ERROR = "Opss! Transaction failed."; 
    private static final String ADDED_SUCCESS = "Project Applicant added successfully!"; 

    private final Project_Applicant_Repository project_applicant_repository;
    private final User_Repository user_repository;
    private final Project_Repository project_repository;

    @Override
    public String execute() {
        if(user_repository.findById(((Apply_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist";
        if(project_repository.findById(((Apply_Request) data).getProject_id()).isEmpty())
            return ERROR + " PROJECT does not exist.";    

        CompositeKey key = new CompositeKey(((Apply_Request) data).getUser_id(), ((Apply_Request) data).getProject_id());
        project_applicant_repository.save(new Project_Applicant(key, false, false, false));
        return ADDED_SUCCESS;
    }
    
}
