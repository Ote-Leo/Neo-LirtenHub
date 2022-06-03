package com.tau.project.configuration;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tau.project.services.commands.project_commands.AddProjectCommand;

@Component
public class Consumer {

    @Autowired
    private AddProjectCommand add_project_command;
    
    @RabbitListener(queues = "project_queue") 
    public void projectConsumer(Message message){
        if(message.getMessage().equals("create_project_success")){
            add_project_command.setData(message.getData());
            System.out.println("Oteeeee" + message.getData().getClass());
            add_project_command.execute();
            System.out.println("Project added successfully!");;
        }
        else if(message.getMessage().equals("create_project_fail")){
            System.out.println("Opss! Transaction failed. OWNER does not exist.");;
        }

        
    }



}
