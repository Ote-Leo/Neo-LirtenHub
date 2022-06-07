package com.tau.project.services.commands.project_commands;

import com.tau.project.repositories.project.Project_Custom;
import com.tau.project.repositories.project.Project_Repository;
import com.tau.project.services.commands.CommandDP;
import com.tau.project.requests.Project_Request;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateProjectCommand extends CommandDP{
    private static final String ERROR = "Opss! Transaction failed."; 
    private static final String UPDATE_SUCCESS = "Project updated successfully!"; 

    private final Project_Repository project_repository;
    private final Project_Custom project_custom;

    @Async("asyncExecutor") 
    @Override
    public String execute() {
        if(((Project_Request) data).getProject_id() == null)
            return ERROR + " Please specify the PROJECT ID.";
        if(project_repository.findById(((Project_Request) data).getProject_id()).isEmpty())
            return ERROR + " PROJECT does not exist.";

        if(((Project_Request) data).getProject_title() != null)
            project_custom.updateAttribute("project_title",((Project_Request) data).getProject_id(), ((Project_Request) data).getProject_title());
        
        if(((Project_Request) data).getProject_description()!= null)
            project_custom.updateAttribute("project_description",((Project_Request) data).getProject_id(), ((Project_Request) data).getProject_description());
        
        if(((Project_Request) data).getStart_date() != null)
            project_custom.updateAttribute("start_date",((Project_Request) data).getProject_id(), ((Project_Request) data).getStart_date());
        
        if(((Project_Request) data).getEnd_date() != null)
            project_custom.updateAttribute("end_date",((Project_Request) data).getProject_id(), ((Project_Request) data).getEnd_date());

        if(((Project_Request) data).getTasks() != null)
            project_custom.updateAttribute("tasks",((Project_Request) data).getProject_id(), ((Project_Request) data).getTasks());

        if(((Project_Request) data).getPrice() != null)
            project_custom.updateAttribute("price",((Project_Request) data).getProject_id(), ((Project_Request) data).getPrice());

        if(((Project_Request) data).getProject_programming_languages() != null)
            project_custom.updateAttribute("project_programming_languages",((Project_Request) data).getProject_id(), ((Project_Request) data).getProject_programming_languages());

        if(((Project_Request) data).getProject_preference_tags() != null)
            project_custom.updateAttribute("project_preference_tags",((Project_Request) data).getProject_id(), ((Project_Request) data).getProject_preference_tags());

        if(((Project_Request) data).getStatus() != null)
            project_custom.updateAttribute("status",((Project_Request) data).getProject_id(), ((Project_Request) data).getStatus());
    
        return UPDATE_SUCCESS;
    }
    
}
