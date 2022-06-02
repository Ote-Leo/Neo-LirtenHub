package com.tau.user.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tau.user.models.UserProfile;
import com.tau.user.requests.Bio_Request;
import com.tau.user.requests.Block_Request;
import com.tau.user.requests.User_Request;
import com.tau.user.services.User_Service;
import com.tau.user.services.commands.biography.AddBioCommand;
import com.tau.user.services.commands.blocking.BlockCommand;
import com.tau.user.services.commands.github_link.AddGithubCommand;
import com.tau.user.services.commands.github_link.DeleteGithubCommand;
import com.tau.user.services.commands.github_link.GetGithubCommand;
import com.tau.user.services.commands.interests.AddInterestCommand;
import com.tau.user.services.commands.interests.DeleteInterestCommand;
import com.tau.user.services.commands.interests.GetInterestCommand;
import com.tau.user.services.commands.preferences.AddPreferenceCommand;
import com.tau.user.services.commands.preferences.DeletePreferenceCommand;
import com.tau.user.services.commands.preferences.GetPreferenceCommand;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class User_Controller {
    private final AddGithubCommand add_github_command; 
    private final DeleteGithubCommand delete_github_command; 
    private final GetGithubCommand get_github_command; 

    private final AddPreferenceCommand add_preference_command; 
    private final DeletePreferenceCommand delete_preference_command; 
    private final GetPreferenceCommand get_preference_command; 

    private final AddInterestCommand add_interest_command; 
    private final DeleteInterestCommand delete_interest_command; 
    private final GetInterestCommand get_interest_command; 

    private final AddBioCommand add_bio_command;
    private final BlockCommand block_command;

    private final User_Service user_service;


    @PostMapping("/user")
    public void postResult(@RequestBody UserProfile user) {
        user_service.add_user(user);
 }

    // ==================PREFERENCE==================================
    @GetMapping(value = "/api/session/usr/project_selection/Prefrence/{user_id}")
    public Object getPrefrences(@PathVariable long user_id) {
        User_Request user_request = new User_Request();
        user_request.setUser_id(user_id);
        get_preference_command.setData(user_request);
        return get_preference_command.execute();
    }

    @PostMapping("/api/session/usr/project_selection/Prefrence/{user_id}")
    public String choosePrefrence(@PathVariable long user_id, @RequestBody  User_Request user) {
        user.setUser_id(user_id);
        add_preference_command.setData(user);
        return add_preference_command.execute();
    }

    @DeleteMapping(value = "/api/session/usr/project_selection/Prefrence/{user_id}")
    public String deletePrefrence(@PathVariable long user_id, @RequestBody  User_Request user) {
        user.setUser_id(user_id);
        delete_preference_command.setData(user);
        return delete_preference_command.execute();
    }

    // ==================GITHUB==================================

    @GetMapping(value = "/api/session/attach_github_link/{user_id}")
    public String getGitHubLink(@PathVariable long user_id) {
        User_Request user_request = new User_Request();
        user_request.setUser_id(user_id);
        get_github_command.setData(user_request);
        return get_github_command.execute();
    }

    @PostMapping("/api/session/attach_github_link/{user_id}")
    public String addGitHubLink(@PathVariable long user_id, @RequestBody User_Request user) {
        user.setUser_id(user_id);
        add_github_command.setData(user);
        return add_github_command.execute();
    }

    @DeleteMapping(value = "/api/session/attach_github_link/{user_id}")
    public String deleteGItHubLink(@PathVariable long user_id) {
        User_Request user_request = new User_Request();
        user_request.setUser_id(user_id);
        delete_github_command.setData(user_request);
        return delete_github_command.execute();
        
    }


    // ==================INTEREST==================================
    @GetMapping(value = "/api/session/usr/hobbies/{user_id}")
    public Object getInterests(@PathVariable long user_id) {
        User_Request user_request = new User_Request();
        user_request.setUser_id(user_id);
        get_interest_command.setData(user_request);
        return get_interest_command.execute();
    }

    @PostMapping("/api/session/usr/hobbies/{user_id}")
    public String addInterest(@PathVariable long user_id, @RequestBody User_Request user) {
        user.setUser_id(user_id);
        add_interest_command.setData(user);
        return add_interest_command.execute();
    }

    @DeleteMapping(value = "/api/session/usr/hobbies/{user_id}")
    public String deleteInterest(@PathVariable long user_id, @RequestBody User_Request user) {
        user.setUser_id(user_id);
        delete_interest_command.setData(user);
        return delete_interest_command.execute();
    }

    // ==================BIOGRAPHY==================================
    @PostMapping(path = "api/user/bio/add_bio")
    public String add_project(@RequestBody Bio_Request bio_request) {
        add_bio_command.setData(bio_request);
        return add_bio_command.execute();   
    }
    // ==================BLOCK==================================
    @PostMapping(path = "api/user/block_user")
    public String postResult(@RequestBody Block_Request block_request) {
        block_command.setData(block_request);
        return block_command.execute();
 }

    // ==================EDIT PROFILE==================================

    // ==================LOCATION==================================

}
