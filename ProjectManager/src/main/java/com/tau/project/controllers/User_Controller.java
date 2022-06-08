package com.tau.project.controllers;

import com.tau.project.models.Users;
import com.tau.project.services.User_Service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
public class User_Controller {
    private final User_Service user_service;

    @PostMapping
    public void postResult(@RequestBody Users user) {
        user_service.add_user(user);
 }


}
