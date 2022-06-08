package com.tau.project.repositories.project_applicant;

import com.tau.project.models.Project_Applicant;
import com.tau.project.models.Project_Applicant.CompositeKey;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class Applicant_CustomImp implements Applicant_Custom{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void accept_project_applier(Long user_id, Long project_id) {
        Query query = new Query();

        query.addCriteria(Criteria.where("_id").is(new CompositeKey(user_id, project_id)));
        //query.addCriteria(Criteria.where("project_id").is(project_id));
        Update update = new Update();
        update.set("isAccepted", true);

        mongoTemplate.updateFirst(query, update, Project_Applicant.class);
    }

    @Override
    public void finish_project(Long user_id, Long project_id) {
        Query query = new Query();

        query.addCriteria(Criteria.where("_id").is(new CompositeKey(user_id, project_id)));
        //query.addCriteria(Criteria.where("project_id").is(project_id));
        Update update = new Update();
        
        update.set("isFinished", true);

        mongoTemplate.updateFirst(query, update, Project_Applicant.class);        
    }

    @Override
    public void pay_user(Long user_id, Long project_id) {
        Query query = new Query();

        query.addCriteria(Criteria.where("_id").is(new CompositeKey(user_id, project_id)));
        //query.addCriteria(Criteria.where("project_id").is(project_id));
        Update update = new Update();
        
        update.set("isPaid", true);

        mongoTemplate.updateFirst(query, update, Project_Applicant.class);  
        
    }
}
