package com.tau.project.configuration;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tau.project.requests.Project_Request;
import com.tau.project.services.commands.project_commands.AddProjectCommand;

@Component
public class Consumer {

    @Autowired
    private AddProjectCommand add_project_command;
    
    @RabbitListener(queues = "project_queue") 
    public void projectConsumer(Message message){

        if(message.getMessage().equals("create_project_success")){
            LinkedHashMap data = (LinkedHashMap) message.getData();

            Long project_id = null, owner_id = null;
            String project_title = (String) data.get("project_title"), 
            project_description = (String) data.get("project_description"), 
            status = (String) data.get("status");
            Date start_date = null, end_date = null;
            ArrayList<String> tasks = (ArrayList<String>) data.get("tasks"), 
            project_programming_languages = (ArrayList<String>) data.get("project_programming_languages"), 
            project_preference_tags = (ArrayList<String>) data.get("project_preference_tags");
            Double price = (Double) data.get("price");

            Integer project_Integer = (Integer) data.get("project_id"),
            owner_Integer = (Integer) data.get("owner_id");

            if(project_Integer != null)
                project_id = new Long(project_Integer);
            
            if(owner_Integer != null)
                owner_id = new Long(owner_Integer);

            if(start_date != null)
                start_date = new Date((Long) data.get("start_date"));

            if(end_date != null)
                end_date = new Date((Long) data.get("end_date"));
            
            Project_Request project_request = new Project_Request(project_id, owner_id, project_title, 
            project_description, status, start_date, end_date, tasks, project_programming_languages, 
            project_preference_tags, price);

            add_project_command.setData(project_request);
            System.out.println(add_project_command.execute());
        }
        else if(message.getMessage().equals("create_project_fail")){
            System.out.println("Opss! Transaction failed. OWNER does not exist.");;
        }
    }
}
