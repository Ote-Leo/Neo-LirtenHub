package com.tau.project.services.commands.project_applicant_commands;

import java.util.ArrayList;

import com.tau.project.models.Project_Applicant;
import com.tau.project.models.Project_Applicant.CompositeKey;
import com.tau.project.repositories.User_Repository;
import com.tau.project.repositories.project.Project_Repository;
import com.tau.project.repositories.project_applicant.Project_Applicant_Repository;
import com.tau.project.services.commands.CommandDP;
import com.tau.project.requests.Apply_Request;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RemoveApplicantCommand extends CommandDP {
    private static final String DELETE_SUCCESS = "Project Applicant deleted successfully!"; 
    private static final String ERROR = "Opss! Transaction failed."; 

    private final Project_Applicant_Repository project_applicant_repsitory;
    private final Project_Repository project_repsitory;
    private final User_Repository user_repsitory;

    @Async("asyncExecutor")
    @Override
    public String execute() {
        if(user_repsitory.findById(((Apply_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist";
        if(project_repsitory.findById(((Apply_Request) data).getProject_id()).isEmpty())
            return ERROR + " PROJECT does not exist.";    
            
        ArrayList<Project_Applicant> list = (ArrayList<Project_Applicant>) project_applicant_repsitory.findAll();
        boolean flag = false;

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId().getProject_id() == ((Apply_Request) data).getProject_id() && 
            list.get(i).getId().getUser_id() == ((Apply_Request) data).getUser_id()){
                flag = true;
                break;
            }

        }

        if(flag){
            CompositeKey key = new CompositeKey(((Apply_Request) data).getUser_id(), ((Apply_Request) data).getProject_id());
            
            Project_Applicant project_Applicant = new Project_Applicant();
            project_Applicant.setId(key);
            
            project_applicant_repsitory.delete(project_Applicant);
            return DELETE_SUCCESS;
        }else
            return ERROR + " There is no such project applicant.";  
    }

}
