package com.tau.project.services.commands.project_commands;

import java.util.ArrayList;

import com.tau.project.models.Project;
import com.tau.project.repositories.User_Repository;
import com.tau.project.repositories.project.Project_Repository;
import com.tau.project.requests.Project_Request;
import com.tau.project.services.commands.CommandDP;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AddProjectCommand extends CommandDP{

    private final Project_Repository project_repository;
    private final User_Repository user_repsitory;

    private static final String ERROR = "Opss! Transaction failed."; 
    private static final String ADDED_SUCCESS = "Project added successfully!"; 

    @Override
    public String execute(){
        System.out.println(data.getClass());
        
        if(user_repsitory.findById(((Project_Request) data).getOwner_id()).isEmpty())
            return ERROR + " OWNER does not exist.";

        project_repository.save(new Project(getNextProjectID(), ((Project_Request) data).getOwner_id(), ((Project_Request) data).getProject_title(), ((Project_Request) data).getProject_description(), ((Project_Request) data).getStatus(), ((Project_Request) data).getStart_date(), ((Project_Request) data).getEnd_date(), ((Project_Request) data).getTasks(), ((Project_Request) data).getProject_programming_languages(), ((Project_Request) data).getProject_preference_tags(), ((Project_Request) data).getPrice()));
        return ADDED_SUCCESS;
    }

    public long getNextProjectID(){
        long max = 0;

        ArrayList<Project> list = (ArrayList<Project>) project_repository.findAll();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getProject_id() > max)
                max = list.get(i).getProject_id();
        }

        return max + 1;
    }

}
