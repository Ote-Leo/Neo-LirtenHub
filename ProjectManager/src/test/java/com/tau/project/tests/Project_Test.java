package com.tau.project.tests;

import java.util.ArrayList;
import java.util.Date;

import com.tau.project.ProjectApplicationTests;
import com.tau.project.models.Project;
import com.tau.project.repositories.project.Project_Repository;
import com.tau.project.requests.Project_Request;
import com.tau.project.services.commands.project_commands.AddProjectCommand;
import com.tau.project.services.commands.project_commands.DeleteProjectCommand;
import com.tau.project.services.commands.project_commands.UpdateProjectCommand;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;

import static org.assertj.core.api.BDDAssertions.then;

@EnableAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Project_Test extends ProjectApplicationTests{
 
    @InjectMocks
    @Autowired
    AddProjectCommand add_project_command;

    @InjectMocks
    @Autowired
    DeleteProjectCommand delete_project_command;

    @InjectMocks
    @Autowired
    UpdateProjectCommand update_project_command;

    @Autowired
    Project_Repository project_repository;
    
    private static long project_id;
    private final String before_status = "started"; 
    private final String after_status = "completed"; 


    @Test
    @Order(1)
    public void create_project(){
        
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("MS1");
        tasks.add("MS2");

        ArrayList<String> project_programming_languages = new ArrayList<>();
        project_programming_languages.add("Java");
        project_programming_languages.add("Javascript");

        ArrayList<String> project_preference_tags = new ArrayList<>();
        project_preference_tags.add("ML");
        project_preference_tags.add("Web");
        
        Date start_date = new Date(1l);
        Date end_date = new Date(2l);

        Project_Request project_request = new Project_Request(null, 2l, 
        "Scalable", "Lirten-Hub", before_status, start_date, end_date, 
        tasks, project_programming_languages, project_preference_tags, (Double) 20.0);    
    
        project_id = add_project_command.getNextProjectID();

        add_project_command.setData(project_request);
        add_project_command.execute();

        ArrayList<Project> list = (ArrayList<Project>) project_repository.findAll();
        boolean flag = false;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getProject_id() == project_id){
                flag = true;
                break;
            }

        }
        then(flag).isEqualTo(true);
        
    
    }

    @Test
    @Order(2)
    public void edit_project(){

        Project_Request project_request = new Project_Request(project_id, null, 
        null, null, "completed", null, null, 
        null, null, null, null);    
        
        ArrayList<Project> list = (ArrayList<Project>) project_repository.findAll();
        boolean flag = false;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getProject_id() == project_request.getProject_id() &&
             list.get(i).getStatus().equals(before_status)){
                flag = true;
                break;
            }

        }
        then(flag).isEqualTo(true);
            
        update_project_command.setData(project_request);
        update_project_command.execute();

        list = (ArrayList<Project>) project_repository.findAll();
        flag = false;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getProject_id() == project_request.getProject_id() &&
             list.get(i).getStatus().equals(after_status)){
                flag = true;
                break;
            }

        }
        then(flag).isEqualTo(true);
        
    
    }

    @Test
    @Order(3)
    public void delete_project(){

        Project_Request project_request = new Project_Request(project_id, null, 
        null, null, null, null, null, 
        null, null, null, null);    
    
        delete_project_command.setData(project_request);
        delete_project_command.execute();

        ArrayList<Project> list = (ArrayList<Project>) project_repository.findAll();
        boolean flag = true;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getProject_id() == project_request.getProject_id()){
                flag = false;
                break;
            }

        }
        then(flag).isEqualTo(true);
        
    
    }
    
}

