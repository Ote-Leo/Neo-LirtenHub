package com.tau.project.services;

import java.sql.Date;
import java.util.ArrayList;

import com.tau.project.repositories.Project_Repsitory;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import com.tau.project.models.Project;


@Service
@AllArgsConstructor
public class Project_Service {
    private static final String ERROR = "Opss! Transaction failed."; 
    private static final String ADDED_SUCCESS = "Project added successfully!"; 
    private static final String UPDATE_SUCCESS = "Project updated successfully!"; 
    private static final String DELETE_SUCCESS = "Project deleted successfully!"; 

    private final Project_Repsitory project_repsitory;

    public String add_project(Project project){
        try {
            project_repsitory.save(project);
            return ADDED_SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public void update_title(Long project_id, String title){
        project_repsitory.updateTitle(project_id, title);
    }

    public void update_description(Long project_id, String description){
        project_repsitory.updateDescription(project_id, description);
    }

    public void update_start_date(Long project_id, Date start_date){
        project_repsitory.updateStartDate(project_id, start_date);
    }

    public void update_end_date(Long project_id, Date end_date){
        project_repsitory.updateEndDate(project_id, end_date);
    }
    
    public void update_tasks(Long project_id, ArrayList<String> tasks){
        project_repsitory.updateTasks(project_id, tasks);
    }

    public void update_price(Long project_id, Double price){
        project_repsitory.updatePrice(project_id, price);
    }

    public void update_languages(Long project_id, ArrayList<String> project_programming_languages){
        project_repsitory.updateLanguages(project_id, project_programming_languages);
    }

    public void update_tags(Long project_id, ArrayList<String> project_preference_tags){
        project_repsitory.updateTags(project_id, project_preference_tags);
    }

    public void update_status(Long project_id, String status){
        project_repsitory.updateStatus(project_id, status);
    }

    public void delete_project(Project project){
        project_repsitory.delete(project);
    }
}
