package com.tau.project.repositories;

import com.tau.project.models.Users;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface User_Repository extends MongoRepository<Users, Long> {
    

}
