package com.tau.user.services;

import com.tau.user.models.UserProfile;
import com.tau.user.repositories.User_Repository;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class User_Service {
    private final User_Repository user_repository;
    //private final User_Custom user_custom;

    // private static final String ADDED_SUCCESS = "added successfully!"; 
    // private static final String DELETE_SUCCESS = "deleted successfully!"; 
    // private static final String ERROR = "Opss! Transaction failed."; 

    // // ==================PREFERENCE==========================================
    // public Object getPrefrences(User_Request user){
    //     Optional<UserProfile> list = user_repository.findById(user.getUser_id());
        
    //     if(list.isEmpty())
    //         return ERROR + " USER does not exist.";
    //     else
    //         return list.get().getProjectPreferenceTags();
    // }

    // public String addPrefrence(User_Request user){
    //     if(user_repository.findById(user.getUser_id()).isEmpty())
    //         return ERROR + " USER does not exist.";

    //     if(user_custom.getPreferences(user.getUser_id()) != null && 
    //     user_custom.getPreferences(user.getUser_id()).contains(user.getPreference()))
    //         return ERROR + " Preference already found!"; 

    //     user_custom.updatePreference(user.getUser_id(), user.getPreference(), "add");
    //     return "Preference " + ADDED_SUCCESS;
    // } 

    // public String deletePrefrence(User_Request user){
    //     if(user_repository.findById(user.getUser_id()).isEmpty())
    //         return ERROR + " USER does not exist.";
    //     user_custom.updatePreference(user.getUser_id(), user.getPreference(), "delete");
    //     return "Preference " + DELETE_SUCCESS;
    // }

    // // ==================GITHUB==========================================


    // public String getGitHubLink(User_Request user){
    //     Optional<UserProfile> list = user_repository.findById(user.getUser_id());
        
    //     if(list.isEmpty())
    //         return ERROR + " USER does not exist.";
    //     else
    //         return list.get().getGitHubLink();
    // }

    // public String addGitHubLink(User_Request user){
    //     if(user_repository.findById(user.getUser_id()).isEmpty())
    //         return ERROR + " USER does not exist.";

    //     user_custom.updateGitHubLink(user.getUser_id(), user.getGithub_link());
    //     return "Github link " + ADDED_SUCCESS;
    // }

    // public String deleteGItHubLink(User_Request user){
    //     if(user_repository.findById(user.getUser_id()).isEmpty())
    //         return ERROR + " USER does not exist.";

    //     user_custom.updateGitHubLink(user.getUser_id(), null);
    //     return "Github link " + DELETE_SUCCESS;
    // }

    // // ==================Interests==========================================

    // public Object getInterests(User_Request user){
    //     Optional<UserProfile> list = user_repository.findById(user.getUser_id());
        
    //     if(list.isEmpty())
    //         return ERROR + " USER does not exist.";
    //     else
    //         return list.get().getInterests();
    // }

    // public String addInterest(User_Request user){
    //     if(user_repository.findById(user.getUser_id()).isEmpty())
    //         return ERROR + " USER does not exist.";
        
    //     if(user_custom.getInterests(user.getUser_id()) != null && 
    //     user_custom.getInterests(user.getUser_id()).contains(user.getInterest()))
    //         return ERROR + " Interest already found!"; 

    //     user_custom.updateInterest(user.getUser_id(), user.getInterest(), "add");
    //     return "Interest " + ADDED_SUCCESS;
    // }

    // public String deleteInterest(User_Request user){
    //     if(user_repository.findById(user.getUser_id()).isEmpty())
    //         return ERROR + " USER does not exist.";

    //     user_custom.updateInterest(user.getUser_id(), user.getInterest(), "delete");
    //     return "Interest " + DELETE_SUCCESS;
    // }

    public void add_user(UserProfile user) {
        user_repository.save(user);
    }
}
