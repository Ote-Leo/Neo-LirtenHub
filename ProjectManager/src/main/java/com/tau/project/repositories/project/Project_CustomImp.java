package com.tau.project.repositories.project;

import com.tau.project.models.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class Project_CustomImp implements Project_Custom{

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public void updateAttribute(String attribute, Long project_id, Object attribute_value) {
        Query query = new Query(Criteria.where("project_id").is(project_id));
        Update update = new Update();
        update.set(attribute, attribute_value);

        mongoTemplate.updateFirst(query, update, Project.class);
    }
}
