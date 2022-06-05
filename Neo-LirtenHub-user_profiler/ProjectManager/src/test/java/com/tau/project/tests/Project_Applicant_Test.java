package com.tau.project.tests;

import java.util.ArrayList;
import java.util.Date;

import com.tau.project.ProjectApplicationTests;
import com.tau.project.models.Project_Applicant;
import com.tau.project.repositories.project_applicant.Project_Applicant_Repository;
import com.tau.project.requests.Accept_Request;
import com.tau.project.requests.Apply_Request;
import com.tau.project.requests.Project_Request;
import com.tau.project.services.commands.project_applicant_commands.AcceptApplicantCommand;
import com.tau.project.services.commands.project_applicant_commands.ApplyApplicantCommand;
import com.tau.project.services.commands.project_applicant_commands.RemoveApplicantCommand;
import com.tau.project.services.commands.project_commands.AddProjectCommand;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;

import static org.assertj.core.api.BDDAssertions.then;

@EnableAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Project_Applicant_Test extends ProjectApplicationTests{
    @InjectMocks
    @Autowired
    AcceptApplicantCommand accept_applicant_command;

    @InjectMocks
    @Autowired
    ApplyApplicantCommand apply_applicant_command;

    @InjectMocks
    @Autowired
    RemoveApplicantCommand remove_applicant_command;
    
    @InjectMocks
    @Autowired
    AddProjectCommand add_project_command;

    

    @Autowired
    Project_Applicant_Repository project_applicant_repository;

    private static long project_id;
    private static long user_id;
    private static long owner_id;

    @Test
    @Order(1)
    public void apply_applicant(){
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("MS1");
        tasks.add("MS2");

        ArrayList<String> project_programming_languages = new ArrayList<>();
        project_programming_languages.add("Java");
        project_programming_languages.add("Javascript");

        ArrayList<String> project_preference_tags = new ArrayList<>();
        project_preference_tags.add("ML");
        project_preference_tags.add("Web");
        
        Date start_date = new Date(1l);
        Date end_date = new Date(2l);

        owner_id = 100l;


        user_id = 101l;

       

        Project_Request project_request = new Project_Request(null, owner_id, 
        "Scalable", "Lirten-Hub", "started", start_date, end_date, 
        tasks, project_programming_languages, project_preference_tags, (Double) 20.0);    
    
        project_id = add_project_command.getNextProjectID();

        add_project_command.setData(project_request);
        add_project_command.execute();
        
        Apply_Request apply_request = new Apply_Request(project_id, user_id);

        apply_applicant_command.setData(apply_request);
        apply_applicant_command.execute();

        ArrayList<Project_Applicant> list = (ArrayList<Project_Applicant>) project_applicant_repository.findAll();
        boolean flag = false;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId().getProject_id() == project_id &&
            list.get(i).getId().getUser_id() == user_id ){
                flag = true;
                break;
            }

        }
        then(flag).isEqualTo(true);
        
    
    }
    
    @Test
    @Order(2)
    public void accept_applicant(){
        ArrayList<Project_Applicant> list = (ArrayList<Project_Applicant>) project_applicant_repository.findAll();
        boolean flag = false;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId().getProject_id() == project_id &&
            list.get(i).getId().getUser_id() == user_id ){
                flag = list.get(i).isAccepted();
                break;
            }

        }
        then(flag).isEqualTo(false);

        Accept_Request accept_request = new Accept_Request(owner_id, project_id, user_id);

        accept_applicant_command.setData(accept_request);
        accept_applicant_command.execute();

        list = (ArrayList<Project_Applicant>) project_applicant_repository.findAll();
        flag = false;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId().getProject_id() == project_id &&
            list.get(i).getId().getUser_id() == user_id ){
                flag = list.get(i).isAccepted();
                break;
            }

        }
        then(flag).isEqualTo(true);

    }

    @Test
    @Order(2)
    public void remove_applicant(){
        ArrayList<Project_Applicant> list = (ArrayList<Project_Applicant>) project_applicant_repository.findAll();
        boolean flag = false;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId().getProject_id() == project_id &&
            list.get(i).getId().getUser_id() == user_id ){
                flag = true;
                break;
            }

        }
        then(flag).isEqualTo(true);

        Apply_Request apply_request = new Apply_Request(project_id, user_id);

        remove_applicant_command.setData(apply_request);
        remove_applicant_command.execute();

        list = (ArrayList<Project_Applicant>) project_applicant_repository.findAll();
        flag = true;
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId().getProject_id() == project_id &&
            list.get(i).getId().getUser_id() == user_id ){
                flag = false;
                break;
            }

        }
        then(flag).isEqualTo(true);

    }

}

