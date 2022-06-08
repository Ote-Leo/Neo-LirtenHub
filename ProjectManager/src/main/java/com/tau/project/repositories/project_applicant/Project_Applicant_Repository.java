package com.tau.project.repositories.project_applicant;

import com.tau.project.models.Project_Applicant;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Project_Applicant_Repository extends MongoRepository<Project_Applicant, Long> {
    
}
