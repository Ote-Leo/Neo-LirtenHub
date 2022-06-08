package com.tau.project.services.commands.project_commands;

import com.tau.project.models.Project;
import com.tau.project.repositories.project.Project_Repository;
import com.tau.project.requests.Project_Request;
import com.tau.project.services.commands.CommandDP;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeleteProjectCommand extends CommandDP{
    
    private static final String ERROR = "Opss! Transaction failed."; 
    private static final String DELETE_SUCCESS = "Project deleted successfully!"; 

    private final Project_Repository project_repository;

    @Override
    public String execute() {

        if(((Project_Request) data).getProject_id() == null)
            return ERROR + " Please specify the PROJECT ID.";
        if(project_repository.findById(((Project_Request) data).getProject_id()).isEmpty())
            return ERROR + " PROJECT does not exist.";
        
        Project project2 = new Project();
        project2.setProject_id(((Project_Request) data).getProject_id());    

        project_repository.delete(project2);
        return DELETE_SUCCESS;

    }
    
}
