package com.tau.project.services;

import com.tau.project.models.Project_Applicant;
import com.tau.project.repositories.Project_Applicant_Repository;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Project_Applicant_Service {
    private final Project_Applicant_Repository project_applicant_repsitory;

    public void accept_project_applier(Project_Applicant project_applicant){
        project_applicant_repsitory.accept_project_applier(project_applicant.getUser_id(), project_applicant.getProject_id());
    }       
    
    public void apply_project_applicant(Project_Applicant project_applicant){
        project_applicant_repsitory.save(project_applicant);
    }       
}
