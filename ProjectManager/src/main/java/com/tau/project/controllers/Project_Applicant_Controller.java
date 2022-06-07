package com.tau.project.controllers;

import com.tau.project.requests.Apply_Request;
import com.tau.project.requests.Accept_Request;
import com.tau.project.services.commands.project_applicant_commands.AcceptApplicantCommand;
import com.tau.project.services.commands.project_applicant_commands.ApplyApplicantCommand;
import com.tau.project.services.commands.project_applicant_commands.FinishProjectCommand;
import com.tau.project.services.commands.project_applicant_commands.PayUserCommand;
import com.tau.project.services.commands.project_applicant_commands.RemoveApplicantCommand;

import org.springframework.web.bind.annotation.PostMapping;
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
    private final FinishProjectCommand finish_project_command;
    private final PayUserCommand pay_user_command;

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

    @PostMapping(path = "finish_project")
    public String finish_project(@RequestBody Apply_Request project_applicant) {
        finish_project_command.setData(project_applicant);
        return finish_project_command.execute();
    }

    @PostMapping(path = "pay_user")
    public String pay_user(@RequestBody Accept_Request project_applicant) {
        pay_user_command.setData(project_applicant);
        return pay_user_command.execute();
    }

}
