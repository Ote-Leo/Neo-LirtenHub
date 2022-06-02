package com.tau.user_profiler.repositories;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.tau.user_profiler.models.UserProfile;

@Component
public class User_CustomImp implements User_Custom{

    @Autowired
    MongoTemplate mongoTemplate;

    
    @Override
    public void updatePreference(long user_id, String prefrence, String method) {
        Query query = new Query(Criteria.where("id").is(user_id));
        Update update = new Update();

        UserProfile userProfile = mongoTemplate.findOne(query, UserProfile.class);

        ArrayList<String> project_preference = new ArrayList<>();

        if(method.equals("add")){
            if(userProfile.getProjectPreferenceTags() == null)
                project_preference.add(prefrence);    
            else{
                project_preference = userProfile.getProjectPreferenceTags();
                project_preference.add(prefrence);
            }
        }else{
            if(userProfile.getProjectPreferenceTags() != null){
                project_preference = userProfile.getProjectPreferenceTags();
                project_preference.remove(prefrence);
            }
        }
        update.set("projectPreferenceTags", project_preference);

        mongoTemplate.updateFirst(query, update, UserProfile.class);
    }

    @Override
    public ArrayList<String> getPreferences(Long user_id) {
        Query query = new Query(Criteria.where("id").is(user_id));

        UserProfile userProfile = mongoTemplate.findOne(query, UserProfile.class);

        return userProfile.getProjectPreferenceTags();
    }


    @Override
    public void updateInterest(long user_id, String interest, String method) {
        Query query = new Query(Criteria.where("id").is(user_id));
        Update update = new Update();

        UserProfile userProfile = mongoTemplate.findOne(query, UserProfile.class);

        ArrayList<String> interests = new ArrayList<>();

        if(method.equals("add")){
            if(userProfile.getInterests() == null){
                interests.add(interest);    
            }else{
                interests = userProfile.getInterests();
                interests.add(interest);
            }
        }else{
            if(userProfile.getInterests() != null){
                interests = userProfile.getInterests();
                interests.remove(interest);
            }
        }
        update.set("interests", interests);
        
        mongoTemplate.updateFirst(query, update, UserProfile.class);
        
    }

    @Override
    public ArrayList<String> getInterests(Long user_id) {
        Query query = new Query(Criteria.where("id").is(user_id));

        UserProfile userProfile = mongoTemplate.findOne(query, UserProfile.class);

        return userProfile.getInterests();
    }

    @Override
    public void updateBio(Long user_id, String Text_Section) {
        Query query = new Query(Criteria.where("id").is(user_id));
        Update update = new Update();
        update.set("bio_section", Text_Section);

        mongoTemplate.updateFirst(query, update, UserProfile.class);
    }

    @Override
    public void updateGitHubLink(long user_id, String name) {
        Query query = new Query(Criteria.where("id").is(user_id));
        Update update = new Update();
        update.set("gitHubLink", name);
    
        mongoTemplate.updateFirst(query, update, UserProfile.class);
        
    }

    @Override
    public void updateBlock(Long user_id, Long blocked_id) {
        Query query = new Query(Criteria.where("id").is(user_id));
        Update update = new Update();

        UserProfile userProfile = mongoTemplate.findOne(query, UserProfile.class);

        ArrayList<Long> blocked_users = new ArrayList<>();

        if(userProfile.getBlocked_users() == null)
            blocked_users.add(blocked_id);    
        else{
            blocked_users = userProfile.getBlocked_users();
            blocked_users.add(blocked_id);
        }
        
        update.set("blocked_users", blocked_users);

        mongoTemplate.updateFirst(query, update, UserProfile.class);
    }

    @Override
    public ArrayList<Long> getBlock(Long user_id) {
        Query query = new Query(Criteria.where("id").is(user_id));

        UserProfile userProfile = mongoTemplate.findOne(query, UserProfile.class);

        return userProfile.getBlocked_users();

    }


    
}
