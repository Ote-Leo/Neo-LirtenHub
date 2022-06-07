package com.tau.project.controllers;

import com.tau.project.requests.Apply_Request;
import com.tau.project.requests.Accept_Request;
import com.tau.project.services.commands.project_applicant_commands.AcceptApplicantCommand;
import com.tau.project.services.commands.project_applicant_commands.ApplyApplicantCommand;
import com.tau.project.services.commands.project_applicant_commands.RemoveApplicantCommand;

import org.springframework.web.bind.annotation.PostMapping;
<<<<<<< HEAD
import org.springframework.amqp.rabbit.core.RabbitTemplate;
=======
>>>>>>> ec9629f7f2f94d741d158ae74166ffce178b857d
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/user/applicant_handler")
@AllArgsConstructor
public class Project_Applicant_Controller {
    private final AcceptApplicantCommand accept_applicant_command;
    private final ApplyApplicantCommand apply_applicant_command;
    private final RemoveApplicantCommand remove_applicant_command;

<<<<<<< HEAD
    RabbitTemplate rabbitTemplate;
=======
>>>>>>> ec9629f7f2f94d741d158ae74166ffce178b857d
    @PostMapping(path = "add_applicant")
    public String add_applicant(@RequestBody Apply_Request project_applicant) {
        apply_applicant_command.setData(project_applicant);
        return apply_applicant_command.execute();
    }

    @DeleteMapping(path = "remove_applicant")
    public String remove_applicant(@RequestBody Apply_Request project_applicant) {
        remove_applicant_command.setData(project_applicant);
        return remove_applicant_command.execute();
    }

    @PutMapping(path = "accept_applicant")
    public String accept_applicant(@RequestBody Accept_Request project_applicant) {
        accept_applicant_command.setData(project_applicant);
        return accept_applicant_command.execute();
    }
}
