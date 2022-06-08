package com.tau.project.repositories.project_applicant;

import java.util.ArrayList;

import com.tau.project.models.Project_Applicant;

public interface Applicant_Custom {
    public void accept_project_applier(Long user_id, Long project_id);
    public void finish_project(Long user_id, Long project_id);
    public void pay_user(Long user_id, Long project_id);
}
