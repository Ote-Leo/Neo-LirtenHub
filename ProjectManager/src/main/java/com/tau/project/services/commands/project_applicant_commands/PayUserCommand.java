package com.tau.project.services.commands.project_applicant_commands;


import java.util.ArrayList;

import com.tau.project.models.Project_Applicant;
import com.tau.project.repositories.User_Repository;
import com.tau.project.repositories.project.Project_Repository;
import com.tau.project.repositories.project_applicant.Applicant_Custom;
import com.tau.project.repositories.project_applicant.Project_Applicant_Repository;
import com.tau.project.services.commands.CommandDP;
import com.tau.project.requests.Accept_Request;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PayUserCommand extends CommandDP {
    private static final String ERROR = "Opss! Transaction failed."; 
    private static final String ACCEPT_SUCCESS = "User paid successfully!"; 

    private final Project_Applicant_Repository project_applicant_repository;
    private final Applicant_Custom applicant_custom;
    private final Project_Repository project_repository;
    private final User_Repository user_repository;


    @Override
    public String execute() {
        if (user_repository.findById(((Accept_Request) data).getUser_id()).isEmpty())
            return ERROR + " USER does not exist";
        if (user_repository.findById(((Accept_Request) data).getOwner_id()).isEmpty())
            return ERROR + " OWNER does not exist";
        if (project_repository.findById(((Accept_Request) data).getProject_id()).isEmpty())
            return ERROR + " PROJECT does not exist.";
        if (project_repository.findById(((Accept_Request) data).getProject_id()).get().getOwner_id() != ((Accept_Request) data)
                .getOwner_id())
            return ERROR + " PROJECT OWNER is the only one allowed to do this transaction.";

        ArrayList<Project_Applicant> list = (ArrayList<Project_Applicant>) project_applicant_repository.findAll();
        boolean flag = false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().getProject_id() == ((Accept_Request) data).getProject_id() &&
                    list.get(i).getId().getUser_id() == ((Accept_Request) data).getUser_id()
                    && list.get(i).isAccepted() && list.get(i).isFinished()) {
                flag = true;
                break;
            }

        }

        if (flag) {
            applicant_custom.pay_user(((Accept_Request) data).getUser_id(), ((Accept_Request) data).getProject_id());
            return ACCEPT_SUCCESS;
        } else
            return ERROR + " There is no such project applicant.";
    }

}