package com.tau.user.tests;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;

import com.tau.user.UserApplicationTests;
import com.tau.user.models.UserAuth;
import com.tau.user.models.UserProfile;
import com.tau.user.repositories.User_Repository;
import com.tau.user.repositories.UserAuth_Repository;
import com.tau.user.requests.Bio_Request;
import com.tau.user.requests.UserAuth_Request;
import com.tau.user.requests.User_Request;
import com.tau.user.services.commands.biography.AddBioCommand;
import com.tau.user.services.commands.coding_languages.AddCodingLanguagesCommand;
import com.tau.user.services.commands.github_link.AddGithubCommand;
import com.tau.user.services.commands.interests.AddInterestCommand;
import com.tau.user.services.commands.location.AddLocationCommand;
import com.tau.user.services.commands.preferences.AddPreferenceCommand;
import com.tau.user.services.commands.registeration.AddLoginCommand;
import com.tau.user.services.commands.registeration.RegisterCommand;

import static org.assertj.core.api.BDDAssertions.then;

@EnableAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class User_Tests extends UserApplicationTests{
    
    @InjectMocks
    @Autowired
    RegisterCommand registerCommand;

    @InjectMocks
    @Autowired
    AddLoginCommand addLoginCommand;

    @InjectMocks
    @Autowired
    AddBioCommand add_bio_command;

    @InjectMocks
    @Autowired
    AddGithubCommand addGithubCommand;

    @InjectMocks
    @Autowired
    AddLocationCommand addLocationCommand;

    @InjectMocks
    @Autowired
    AddCodingLanguagesCommand add_coding_command;

    @InjectMocks
    @Autowired
    AddInterestCommand addInterestCommand;

    @InjectMocks
    @Autowired
    AddPreferenceCommand addPreferenceCommand;

    @Autowired
    User_Repository user_repository;

    @Autowired
    UserAuth_Repository userAuth_Repository;;
    
    
    public long getNextId(){
        long max = 0;

        ArrayList<UserProfile> list = (ArrayList<UserProfile>) user_repository.findAll();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId() > max)
                max = list.get(i).getId();
        }

        return max + 1;
    }

    @Test
    @Order(1)
    public void register() throws NoSuchAlgorithmException{
        User_Request user_request = new User_Request();

        user_request.setUser_id(getNextId());
        user_request.setFirst_name("Mohamed");
        user_request.setLast_name("Ashraf");
        user_request.setEmail("mohzashraf99@gmail.com");
        user_request.setPassword("ashraf");


        registerCommand.setData(user_request);
        registerCommand.execute();

        ArrayList<UserProfile> list = (ArrayList<UserProfile>) user_repository.findAll();
        boolean flag = false;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getEmail().equals("mohzashraf99@gmail.com")){
                flag = true;
                break;
            }

        }
        then(flag).isEqualTo(true);
    
    }

    @Test
    @Order(2)
    public void login() throws NoSuchAlgorithmException{
        UserAuth_Request userAuth_Request = new UserAuth_Request();


        ArrayList<UserProfile> list = (ArrayList<UserProfile>) user_repository.findAll();
        Long user_id = null;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getEmail().equals("mohzashraf99@gmail.com")){
                user_id = list.get(i).getId();
                break;
            }

        }
        System.out.println(user_id);

        userAuth_Request.setUser_id(user_id);
        userAuth_Request.setPassword("ashraf");


        addLoginCommand.setData(userAuth_Request);
        System.out.println( addLoginCommand.execute());
        
        ArrayList<UserAuth> list2 = (ArrayList<UserAuth>) userAuth_Repository.findAll();
        boolean flag = false;
        
        for(int i = 0; i < list2.size(); i++){
            if(list2.get(i).getUser_id().equals(user_id) && list2.get(i).getLogoutTime() == null){
                System.out.println("ashraffff");
                flag = true;
                break;
            }

        }
        then(flag).isEqualTo(true);

    }
    
    @Test
    @Order(3)
    public void add_bio(){
        UserAuth_Request userAuth_Request = new UserAuth_Request();


        ArrayList<UserProfile> list = (ArrayList<UserProfile>) user_repository.findAll();
        Long user_id = null;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getEmail().equals("mohzashraf99@gmail.com")){
                user_id = list.get(i).getId();
                break;
            }

        }
        
        Bio_Request bio_Request = new Bio_Request();
        bio_Request.setUser_id(user_id);
        bio_Request.setText_section("Askora Amir was here");

        add_bio_command.setData(bio_Request);
        System.out.println( add_bio_command.execute());
        
        ArrayList<UserProfile> list2 = (ArrayList<UserProfile>) user_repository.findAll();
        
        String bio = "";

        for(int i = 0; i < list2.size(); i++){
            if(list2.get(i).getId().equals(user_id)){
                bio = list2.get(i).getBio_section();
                break;
            }

        }
        then(bio).isEqualTo("Askora Amir was here");

    }

    @Test
    @Order(4)
    public void add_coding(){
        UserAuth_Request userAuth_Request = new UserAuth_Request();


        ArrayList<UserProfile> list = (ArrayList<UserProfile>) user_repository.findAll();
        Long user_id = null;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getEmail().equals("mohzashraf99@gmail.com")){
                user_id = list.get(i).getId();
                break;
            }

        }
        
        User_Request user_Request = new User_Request();
        user_Request.setUser_id(user_id);
        user_Request.setCoding_language("Java");

        add_coding_command.setData(user_Request);
        System.out.println( add_coding_command.execute());
        
        ArrayList<UserProfile> list2 = (ArrayList<UserProfile>) user_repository.findAll();
        
        boolean flag = false;

        for(int i = 0; i < list2.size(); i++){
            if(list2.get(i).getId().equals(user_id) && list2.get(i).getCodingLanguages().contains("Java")){
                flag = true;
                break;
            }

        }
        then(flag).isEqualTo(true);

    }

    @Test
    @Order(5)
    public void add_github(){
        UserAuth_Request userAuth_Request = new UserAuth_Request();


        ArrayList<UserProfile> list = (ArrayList<UserProfile>) user_repository.findAll();
        Long user_id = null;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getEmail().equals("mohzashraf99@gmail.com")){
                user_id = list.get(i).getId();
                break;
            }

        }
        
        User_Request user_Request = new User_Request();
        user_Request.setUser_id(user_id);
        user_Request.setGithub_link("github.com");

        addGithubCommand.setData(user_Request);
        System.out.println( addGithubCommand.execute());
        
        ArrayList<UserProfile> list2 = (ArrayList<UserProfile>) user_repository.findAll();
        
        boolean flag = false;

        for(int i = 0; i < list2.size(); i++){
            if(list2.get(i).getId().equals(user_id) && list2.get(i).getGitHubLink().equals("github.com")){
                flag = true;
                break;
            }

        }
        then(flag).isEqualTo(true);

    }

    @Test
    @Order(6)
    public void add_interests(){
        UserAuth_Request userAuth_Request = new UserAuth_Request();


        ArrayList<UserProfile> list = (ArrayList<UserProfile>) user_repository.findAll();
        Long user_id = null;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getEmail().equals("mohzashraf99@gmail.com")){
                user_id = list.get(i).getId();
                break;
            }

        }
        
        User_Request user_Request = new User_Request();
        user_Request.setUser_id(user_id);
        user_Request.setInterest("PS");

        addInterestCommand.setData(user_Request);
        System.out.println( addInterestCommand.execute());
        
        ArrayList<UserProfile> list2 = (ArrayList<UserProfile>) user_repository.findAll();
        
        boolean flag = false;

        for(int i = 0; i < list2.size(); i++){
            if(list2.get(i).getId().equals(user_id) && list2.get(i).getInterests().contains("PS")){
                flag = true;
                break;
            }

        }
        then(flag).isEqualTo(true);

    }

    @Test
    @Order(7)
    public void add_preference(){
        UserAuth_Request userAuth_Request = new UserAuth_Request();


        ArrayList<UserProfile> list = (ArrayList<UserProfile>) user_repository.findAll();
        Long user_id = null;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getEmail().equals("mohzashraf99@gmail.com")){
                user_id = list.get(i).getId();
                break;
            }

        }
        
        User_Request user_Request = new User_Request();
        user_Request.setUser_id(user_id);
        user_Request.setPreference("ML");

        addPreferenceCommand.setData(user_Request);
        System.out.println( addPreferenceCommand.execute());
        
        ArrayList<UserProfile> list2 = (ArrayList<UserProfile>) user_repository.findAll();
        
        boolean flag = false;

        for(int i = 0; i < list2.size(); i++){
            if(list2.get(i).getId().equals(user_id) && list2.get(i).getProjectPreferenceTags().contains("ML")){
                flag = true;
                break;
            }

        }
        then(flag).isEqualTo(true);

    }

    @Test
    @Order(8)
    public void add_location(){
        UserAuth_Request userAuth_Request = new UserAuth_Request();


        ArrayList<UserProfile> list = (ArrayList<UserProfile>) user_repository.findAll();
        Long user_id = null;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getEmail().equals("mohzashraf99@gmail.com")){
                user_id = list.get(i).getId();
                break;
            }

        }
        
        User_Request user_Request = new User_Request();
        user_Request.setUser_id(user_id);
        user_Request.setLocation("Cairo");

        addLocationCommand.setData(user_Request);
        System.out.println( addLocationCommand.execute());
        
        ArrayList<UserProfile> list2 = (ArrayList<UserProfile>) user_repository.findAll();
        
        boolean flag = false;

        for(int i = 0; i < list2.size(); i++){
            if(list2.get(i).getId().equals(user_id) && list2.get(i).getLocation().equals("Cairo")){
                flag = true;
                break;
            }

        }
        then(flag).isEqualTo(true);

    }
    
}

