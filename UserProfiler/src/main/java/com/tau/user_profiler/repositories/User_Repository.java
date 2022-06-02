package com.tau.user_profiler.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tau.user_profiler.models.UserProfile;

public interface User_Repository  extends MongoRepository<UserProfile, Long> {
    
}

