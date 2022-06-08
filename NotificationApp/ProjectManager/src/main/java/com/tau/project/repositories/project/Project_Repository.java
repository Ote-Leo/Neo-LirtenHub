package com.tau.project.repositories.project;

import com.tau.project.models.Project;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Project_Repository extends MongoRepository<Project, Long> {
    
}
