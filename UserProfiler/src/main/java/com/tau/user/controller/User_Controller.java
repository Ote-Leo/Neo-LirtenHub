package com.tau.user.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.tau.user.RabbitMQConfiguration.Message;
import com.tau.user.RabbitMQConfiguration.RabbitMQConfiguration;
import com.tau.user.models.UserProfile;
import com.tau.user.requests.Ban_Request;
import com.tau.user.requests.Bio_Request;
import com.tau.user.requests.Block_Request;
import com.tau.user.requests.Report_Request;
import com.tau.user.requests.UserAuth_Request;
import com.tau.user.requests.User_Request;
import com.tau.user.services.commands.banning.BanCommand;
import com.tau.user.services.commands.biography.AddBioCommand;
import com.tau.user.services.commands.blocking.BlockCommand;
import com.tau.user.services.commands.coding_languages.AddCodingLanguagesCommand;
import com.tau.user.services.commands.edit_profile.EditFirstNameCommand;
import com.tau.user.services.commands.edit_profile.EditLastNameCommand;
import com.tau.user.services.commands.edit_profile.EditPasswordCommand;
import com.tau.user.services.commands.github_link.AddGithubCommand;
import com.tau.user.services.commands.github_link.DeleteGithubCommand;
import com.tau.user.services.commands.github_link.GetGithubCommand;
import com.tau.user.services.commands.interests.AddInterestCommand;
import com.tau.user.services.commands.interests.DeleteInterestCommand;
import com.tau.user.services.commands.interests.GetInterestCommand;
import com.tau.user.services.commands.location.AddLocationCommand;
import com.tau.user.services.commands.preferences.AddPreferenceCommand;
import com.tau.user.services.commands.preferences.DeletePreferenceCommand;
import com.tau.user.services.commands.preferences.GetPreferenceCommand;
import com.tau.user.services.commands.registeration.AddLoginCommand;
import com.tau.user.services.commands.registeration.RegisterCommand;
import com.tau.user.services.commands.registeration.UpdateLogoutCommand;
import com.tau.user.services.commands.reporting.GetReportsCommand;
import com.tau.user.services.commands.reporting.ReportCommand;

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
    private final BanCommand ban_command;
    private final ReportCommand report_command;

    private final EditFirstNameCommand editFirstNameCommand;
    private final EditLastNameCommand editLastNameCommand;
    private final EditPasswordCommand editPasswordCommand;

    private final AddCodingLanguagesCommand addCodingLanguagesCommand; 
    private final GetReportsCommand getReport_command;

    private final AddLocationCommand add_location_manual;

    private final AddLoginCommand add_login_command;
    private final UpdateLogoutCommand update_logout_command;
    private final RegisterCommand registerCommand;


    @Autowired
    RabbitTemplate template;

 
 // =======================User Authentication===========================//
 @PostMapping(path = "api/user/userauth/register")
 public String register(@RequestBody User_Request user_request) throws NoSuchAlgorithmException {
     registerCommand.setData(user_request);
     return registerCommand.execute();   
 }

 @PostMapping(path = "api/user/userauth/add_login")
 public String add_login(@RequestBody UserAuth_Request userauth_request) throws NoSuchAlgorithmException {
     add_login_command.setData(userauth_request);
     return add_login_command.execute();   
 }
 
 @PutMapping("/api/session/usr/profile/editLogout/{User_ID}")
 public String updatelogout(@PathVariable Long User_ID){
     UserAuth_Request userauth_request = new UserAuth_Request();
   
     userauth_request.setUser_id(User_ID);
     update_logout_command.setData(userauth_request);
     return update_logout_command.execute();
 }
 

    // ==================PREFERENCE==================================
    @GetMapping(value = "/api/session/usr/project_selection/preference/{user_id}")
    //@Cacheable(value = "preference", key = "#user_id")
    public Object getPrefrences(@PathVariable long user_id) {
        User_Request user_request = new User_Request();
        user_request.setUser_id(user_id);
        get_preference_command.setData(user_request);
        return get_preference_command.execute();
    }

    @PostMapping("/api/session/usr/project_selection/add_preference/{user_id}")
    public String choosePrefrence(@PathVariable long user_id, @RequestBody  User_Request user) {
        user.setUser_id(user_id);
        add_preference_command.setData(user);
        return add_preference_command.execute();
    }

    @DeleteMapping(value = "/api/session/usr/project_selection/delete_preference/{user_id}")
    public String deletePrefrence(@PathVariable long user_id, @RequestBody  User_Request user) {
        user.setUser_id(user_id);
        delete_preference_command.setData(user);
        return delete_preference_command.execute();
    }

    // ==================GITHUB==================================

    @GetMapping(value = "/api/session/attach_github_link/{user_id}")
    //@Cacheable(value = "github_link", key = "#user_id")
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

    @DeleteMapping(value = "/api/session/delete_github_link/{user_id}")
    public String deleteGItHubLink(@PathVariable long user_id) {
        User_Request user_request = new User_Request();
        user_request.setUser_id(user_id);
        delete_github_command.setData(user_request);
        return delete_github_command.execute();
        
    }


    // ==================INTEREST==================================
    @GetMapping(value = "/api/session/usr/hobbies/{user_id}")
    //@Cacheable(value = "interests", key = "#user_id")
    public Object getInterests(@PathVariable long user_id) {
        User_Request user_request = new User_Request();
        user_request.setUser_id(user_id);
        get_interest_command.setData(user_request);
        return get_interest_command.execute();
    }

    @PostMapping("/api/session/usr/add_interest/{user_id}")
    public String addInterest(@PathVariable long user_id, @RequestBody User_Request user) {
        user.setUser_id(user_id);
        add_interest_command.setData(user);
        return add_interest_command.execute();
    }

    @DeleteMapping(value = "/api/session/usr/delete_interest/{user_id}")
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
    @PutMapping("/api/session/usr/profile/editFirstName/{User_ID}")
    public String editFirstName(@PathVariable Long User_ID, @RequestBody User_Request user_request){
        user_request.setUser_id(User_ID);
        editFirstNameCommand.setData(user_request);
        return editFirstNameCommand.execute();
    }
    

    @PutMapping("/api/session/usr/profile/editLastName/{User_ID}")
    public String editLastName(@PathVariable Long User_ID, @RequestBody User_Request user_request){
        user_request.setUser_id(User_ID);
        editLastNameCommand.setData(user_request);
        return editLastNameCommand.execute();
    }


    @PutMapping("/api/session/usr/profile/editPassword/{User_ID}")
    public String editPassword(@PathVariable Long User_ID, @RequestBody User_Request user_request) throws NoSuchAlgorithmException{
        user_request.setUser_id(User_ID);
        editPasswordCommand.setData(user_request);
        return editPasswordCommand.execute();
    }

    @PostMapping(path = "/api/session/usr/report")
    public String add_report(@RequestBody Report_Request report_request) {
        report_command.setData(report_request);
        return report_command.execute();       
 }

 @PostMapping("/api/session/usr/codingLanguages/{user_id}")
    public String addLanguage(@PathVariable long user_id, @RequestBody User_Request user) {
        user.setUser_id(user_id);
        addCodingLanguagesCommand.setData(user);
        return addCodingLanguagesCommand.execute();
    }

    @GetMapping(value = "/api/session/moderator/project_selection/get_report/{user_id}")
    //@Cacheable(value = "reporting",key = "#user_id")
    public Object getReport(@PathVariable long user_id) {
        User_Request user = new User_Request();
        user.setUser_id(user_id);
        getReport_command.setData(user);
        return getReport_command.execute();
    }


    @PostMapping("/api/session/usr/add_location/manual/{user_id}")
    public String add_location_manually(@PathVariable long user_id, @RequestBody User_Request user) {
        user.setUser_id(user_id);
        add_location_manual.setData(user);
        return add_location_manual.execute();
    }

    @PostMapping("/api/session/usr/add_location/automatic/{user_id}")
    public void add_location_automatically(@PathVariable long user_id) {
        User_Request user_request = new User_Request();
        user_request.setUser_id(user_id);
        Message message = new Message();
        message.setData(user_request);

        template.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.GEO_ROUTING_KEY, message);      
    
    }

    @PostMapping("/api/session/moderator/ban_user/{user_id}")
    public String ban_request(@PathVariable long user_id, @RequestBody Ban_Request ban_request) {
        ban_request.setUser_id(user_id);
        ban_command.setData(ban_request);
        return ban_command.execute();
    }
}

