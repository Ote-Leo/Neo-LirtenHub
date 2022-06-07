package com.tau.user.repositories;

import com.tau.user.models.UserAuth;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAuth_Repository  extends MongoRepository<UserAuth, Long> {
    
}

