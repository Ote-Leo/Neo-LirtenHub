package com.tau.user.repositories;

import com.tau.user.models.UserProfile;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAuth_Repository  extends MongoRepository<UserProfile, Long> {
    
}

