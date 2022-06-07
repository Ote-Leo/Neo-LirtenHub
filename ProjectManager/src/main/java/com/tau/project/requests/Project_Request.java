package com.tau.project.requests;

import java.util.Date;
<<<<<<< HEAD
import java.io.Serializable;
=======
>>>>>>> ec9629f7f2f94d741d158ae74166ffce178b857d
import java.util.ArrayList;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
<<<<<<< HEAD
public class Project_Request implements Serializable  {
=======
public class Project_Request {
>>>>>>> ec9629f7f2f94d741d158ae74166ffce178b857d
    private final Long project_id, owner_id;
    private final String project_title, project_description, status;
    private final Date start_date, end_date;
    private final ArrayList<String> tasks, project_programming_languages, project_preference_tags;
    private final Double price;

    public Project_Request(Long project_id, Long owner_id, String project_title, String project_description, String status, Date start_date, Date end_date, ArrayList<String> tasks, ArrayList<String> project_programming_languages, ArrayList<String> project_preference_tags, Double price ){
        this.project_id = project_id;
        this.owner_id = owner_id;
        this.project_title = project_title;
        this.project_description = project_description;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
        this.tasks = tasks;
        this.project_programming_languages = project_programming_languages;
        this.project_preference_tags = project_preference_tags;
        this.price = price;

    }

    public Project_Request(){
        this.project_id = null;
        this.owner_id = null;
        this.project_title = null;
        this.project_description = null;
        this.status = null;
        this.start_date = null;
        this.end_date = null;
        this.tasks = null;
        this.project_programming_languages = null;
        this.project_preference_tags = null;
        this.price = null;

    }

}
