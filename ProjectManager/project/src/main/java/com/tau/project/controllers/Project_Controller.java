package com.tau.project.controllers;

import com.tau.project.models.Project;
import com.tau.project.services.Project_Service;
import com.tau.project.services.User_Service;

//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/user/project_handler")
@AllArgsConstructor
public class Project_Controller {
    private final Project_Service project_service;

    @PostMapping(path = "create_project")
    public String add_project(@RequestBody Project project) {
        System.out.println("ashraf is here");
        return project_service.add_project(project);   
    }

    @DeleteMapping(path = "delete_project")
    public void delete_project(@RequestBody Project project) {
        project_service.delete_project(project);
    }

    @PutMapping(path = "update_project")
    public String update_project(@RequestBody Project project) {
        System.out.println(project);
        if(project.getProject_id().equals(""))
            return "Please identify the PROJECT ID.";

        if(! project.getProject_title().equals(""))
            project_service.update_title( project.getProject_id(), project.getProject_title());
        
        if(! project.getProject_description().equals(""))
            project_service.update_description( project.getProject_id(), project.getProject_description());
        
        if(! project.getStart_date().equals(""))
            project_service.update_start_date( project.getProject_id(), project.getStart_date());
        
        if(! project.getEnd_date().equals(""))
            project_service.update_end_date( project.getProject_id(), project.getEnd_date());

        if(! project.getTasks().equals(""))
            project_service.update_tasks( project.getProject_id(), project.getTasks());

        if(! project.getPrice().equals(""))
            project_service.update_price( project.getProject_id(), project.getPrice());

        if(! project.getProject_programming_languages().equals(""))
            project_service.update_languages( project.getProject_id(), project.getProject_programming_languages());

        if(! project.getProject_preference_tags().equals(""))
            project_service.update_tags( project.getProject_id(), project.getProject_preference_tags());

        if(! project.getStatus().equals(""))
            project_service.update_status( project.getProject_id(), project.getStatus());

        return "Updated Successfully!";        
    }

}