package com.tau.project.configuration;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tau.project.models.Project_Applicant;
import com.tau.project.repositories.project_applicant.Project_Applicant_Repository;
import com.tau.project.requests.Accept_Request;
import com.tau.project.requests.Apply_Request;
import com.tau.project.requests.Notify_Request;
import com.tau.project.requests.Project_Request;
import com.tau.project.services.commands.project_applicant_commands.AcceptApplicantCommand;
import com.tau.project.services.commands.project_applicant_commands.ApplyApplicantCommand;
import com.tau.project.services.commands.project_applicant_commands.FinishProjectCommand;
import com.tau.project.services.commands.project_applicant_commands.PayUserCommand;
import com.tau.project.services.commands.project_applicant_commands.RemoveApplicantCommand;
import com.tau.project.services.commands.project_commands.AddProjectCommand;

@Component
public class Consumer {

    @Autowired
    private AddProjectCommand add_project_command;

    @Autowired
    private AcceptApplicantCommand accept_applicant_command;
    
    @Autowired
    private ApplyApplicantCommand apply_applicant_command;
    
    @Autowired
    private RemoveApplicantCommand remove_applicant_command;
    
    @Autowired
    private FinishProjectCommand finish_project_command;
    
    @Autowired
    private PayUserCommand pay_user_command;

    @Autowired
    private Project_Applicant_Repository project_applicant_repository;

    @Autowired
    RabbitTemplate template;
    
    @RabbitListener(queues = "project_queue") 
    public void projectConsumer(Message message){
        System.out.println(message.getMessage());

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
            String output = add_project_command.execute();

            System.out.println("Output: " + output);
        }
        else if(message.getMessage().equals("user_not_exist")){
            System.out.println("Output: Opss! Transaction failed. USER does not exist.");;
        }
        else if(message.getMessage().equals("owner_not_exist")){
            System.out.println("Output: Opss! Transaction failed. OWNER does not exist.");;
        }
        else if(message.getMessage().equals("login_fail")){
            System.out.println("Output: Opss! Transaction failed.  You are not logged in.");;
        }
        else if(message.getMessage().equals("add_applicant_success")){
            LinkedHashMap data = (LinkedHashMap) message.getData();

            Long project_id = null, user_id = null;

            Integer project_Integer = (Integer) data.get("project_id"),
            user_Integer = (Integer) data.get("user_id");

            if(project_Integer != null)
                project_id = new Long(project_Integer);
            
            if(user_Integer != null)
                user_id = new Long(user_Integer);

            Apply_Request project_applicant = new Apply_Request(project_id, user_id);

            apply_applicant_command.setData(project_applicant);
            String output = apply_applicant_command.execute();
            System.out.println("Output: " + output);
        }
        else if(message.getMessage().equals("remove_applicant_success")){
            LinkedHashMap data = (LinkedHashMap) message.getData();

            Long project_id = null, user_id = null;

            Integer project_Integer = (Integer) data.get("project_id"),
            user_Integer = (Integer) data.get("user_id");

            if(project_Integer != null)
                project_id = new Long(project_Integer);
            
            if(user_Integer != null)
                user_id = new Long(user_Integer);

            Apply_Request project_applicant = new Apply_Request(project_id, user_id);

            remove_applicant_command.setData(project_applicant);
            String output = remove_applicant_command.execute();
            System.out.println("Output: " + output);
        }
        else if(message.getMessage().equals("accept_applicant_success")){
            LinkedHashMap data = (LinkedHashMap) message.getData();

            Long owner_id = null, project_id = null, user_id = null;

            Integer owner_Integer = (Integer) data.get("owner_id"),
            project_Integer = (Integer) data.get("project_id"),
            user_Integer = (Integer) data.get("user_id");

            if(owner_Integer != null)
                owner_id = new Long(owner_Integer);

            if(project_Integer != null)
                project_id = new Long(project_Integer);
            
            if(user_Integer != null)
                user_id = new Long(user_Integer);

            Accept_Request project_applicant = new Accept_Request(owner_id ,project_id, user_id);

            accept_applicant_command.setData(project_applicant);
            String output = accept_applicant_command.execute();
            System.out.println("Output: " + output);
        }
        else if(message.getMessage().equals("finish_project_success")){
            LinkedHashMap data = (LinkedHashMap) message.getData();

            Long project_id = null, user_id = null;

            Integer project_Integer = (Integer) data.get("project_id"),
            user_Integer = (Integer) data.get("user_id");

            if(project_Integer != null)
                project_id = new Long(project_Integer);
            
            if(user_Integer != null)
                user_id = new Long(user_Integer);

            Apply_Request project_applicant = new Apply_Request(project_id, user_id);

            finish_project_command.setData(project_applicant);
            String output = finish_project_command.execute();
            System.out.println("Output: " + output);
        }
        else if(message.getMessage().equals("pay_user_success")){
            LinkedHashMap data = (LinkedHashMap) message.getData();

            Long owner_id = null, project_id = null, user_id = null;

            Integer owner_Integer = (Integer) data.get("owner_id"),
            project_Integer = (Integer) data.get("project_id"),
            user_Integer = (Integer) data.get("user_id");

            if(owner_Integer != null)
                owner_id = new Long(owner_Integer);

            if(project_Integer != null)
                project_id = new Long(project_Integer);
            
            if(user_Integer != null)
                user_id = new Long(user_Integer);

            Accept_Request project_applicant = new Accept_Request(owner_id, project_id, user_id);

            pay_user_command.setData(project_applicant);
            String output = pay_user_command.execute();
            System.out.println("Output: " + output);
        }else if(message.getMessage().equals("accept_applicant")){
            LinkedHashMap data = (LinkedHashMap) message.getData();

            Long user_id = null;

            Integer user_Integer = (Integer) data.get("accepted_id");
            
            if(user_Integer != null)
                user_id = new Long(user_Integer);

            System.out.println("user_id" +user_id);

            ArrayList<Project_Applicant> list = (ArrayList<Project_Applicant>) project_applicant_repository.findAll();

            ArrayList<Long> myProjects = new ArrayList<Long>();

            for (Project_Applicant applicant : list) {
                if(applicant.isAccepted() && applicant.getId().getUser_id().equals(user_id))
                    myProjects.add(applicant.getId().getProject_id());
            }

            System.out.println("myProjects" +myProjects.size());

            ArrayList<Notify_Request> output = new ArrayList<Notify_Request>();

            Notify_Request notify_request;

            for (Project_Applicant applicant : list) {
                if(applicant.isAccepted() && 
                !applicant.getId().getUser_id().equals(user_id) &&
                myProjects.contains(applicant.getId().getProject_id())){

                    notify_request = new Notify_Request();
                    notify_request.setAccepted_id(user_id);
                    notify_request.setUser_id(applicant.getId().getUser_id());
                    output.add(notify_request);
                }
            }
            System.out.println(output.size());

            Message response = new Message();
            response.setMessage("notify");
            response.setMethod("notify");

            response.setData(output);

            template.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.NOTIFICATION_ROUTING_KEY, response);      
            
        }
        
    }
}
