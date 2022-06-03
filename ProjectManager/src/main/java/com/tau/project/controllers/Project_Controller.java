package com.tau.project.controllers;

import com.tau.project.configuration.Message;
import com.tau.project.configuration.RabbitMQConfiguration;
import com.tau.project.requests.Project_Request;
// import com.tau.project.services.commands.project_commands.AddProjectCommand;
import com.tau.project.services.commands.project_commands.DeleteProjectCommand;
import com.tau.project.services.commands.project_commands.UpdateProjectCommand;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/user/project_handler")
@AllArgsConstructor
public class Project_Controller {
    // private final AddProjectCommand add_project_command;
    private final DeleteProjectCommand delete_project_command;
    private final UpdateProjectCommand update_project_command;

    @Autowired
    RabbitTemplate template;


    // @PostMapping(path = "create_project")
    // public String add_project(@RequestBody Project_Request project) {
    //     add_project_command.setData(project);
    //     template.convertAndSend(RabbitMQConfiguration.USER_EXCHANGE, RabbitMQConfiguration.USER_ROUTING_KEY, project);      
    //     return add_project_command.execute();   

    // }

    @PostMapping(path = "create_project")
    public void add_project(@RequestBody Project_Request project) {
        //add_project_command.setData(project);

        Message message = new Message();
        message.setMethod("create_project");
        message.setOwner_id(project.getOwner_id());
        message.setData(project);

        template.convertAndSend(RabbitMQConfiguration.USER_EXCHANGE, RabbitMQConfiguration.USER_ROUTING_KEY, message);      
        
        // if(create_project)
        //     return add_project_command.execute();   
        // else
        //     return ERROR + " OWNER does not exist.";

    }

    @DeleteMapping(path = "delete_project")
    public String delete_project(@RequestBody Project_Request project) {
        delete_project_command.setData(project);      
        return delete_project_command.execute();
    }

    @PutMapping(path = "update_project")
    public String update_project(@RequestBody Project_Request project) {
        update_project_command.setData(project);      
        return update_project_command.execute();
    }

}