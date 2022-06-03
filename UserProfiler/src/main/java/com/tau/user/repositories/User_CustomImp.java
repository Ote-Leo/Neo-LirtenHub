package com.tau.user.repositories;


import java.util.ArrayList;

import com.tau.user.models.Report;
import com.tau.user.models.UserProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    @Override
    public void updateFirstName(Long user_id, String first_name) {
        Query query = new Query(Criteria.where("id").is(user_id));
        Update update = new Update();
        update.set("firstName", first_name);
    
        mongoTemplate.updateFirst(query, update, UserProfile.class);
    }

    @Override
    public void updateLastName(Long user_id, String last_name) {
        Query query = new Query(Criteria.where("id").is(user_id));
        Update update = new Update();
        update.set("lastName", last_name);
    
        mongoTemplate.updateFirst(query, update, UserProfile.class);
    }

    @Override
    public void updatePassword(Long user_id, String password) throws NoSuchAlgorithmException {
        Query query = new Query(Criteria.where("id").is(user_id));
        Update update = new Update();
        try {
            MessageDigest message_digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = message_digest.digest(password.getBytes());
            String passwordHash = new String(hashBytes);
            update.set("password", passwordHash);
        } catch (Exception e) {
            
        }
    
        mongoTemplate.updateFirst(query, update, UserProfile.class);
    }

    @Override
    public ArrayList<Report> getReport(Long user_id) {
        Query query = new Query(Criteria.where("id").is(user_id));

        UserProfile userProfile = mongoTemplate.findOne(query, UserProfile.class);

        return userProfile.getReported_users();
    }

    @Override
    public void updateReport(Long user_id, Long reported_id, String report_description) {
        Query query = new Query(Criteria.where("id").is(user_id));
        Update update = new Update();

        UserProfile userProfile = mongoTemplate.findOne(query, UserProfile.class);

        ArrayList<Report> reported_users = new ArrayList<>();

        Report report = new Report(reported_id, report_description);

        if(userProfile.getReported_users() == null)
            reported_users.add(report);    
        else{
            reported_users = userProfile.getReported_users();
            reported_users.add(report);
        }
        
        update.set("reported_users", reported_users);

        mongoTemplate.updateFirst(query, update, UserProfile.class);
        
    }

    @Override
    public void updateLanguage(long user_id, String coding_language) {
        Query query = new Query(Criteria.where("id").is(user_id));
        Update update = new Update();

        UserProfile userProfile = mongoTemplate.findOne(query, UserProfile.class);

        ArrayList<String> languages = new ArrayList<>();

            if(userProfile.getCodingLanguages() == null){
                languages.add(coding_language);    
            }else{
                languages = userProfile.getInterests();
                languages.add(coding_language);
            }
        
        
        update.set("codingLanguages", languages);
        
        mongoTemplate.updateFirst(query, update, UserProfile.class);        
    }

    @Override
    public ArrayList<String> getLanguages(Long user_id) {
        Query query = new Query(Criteria.where("id").is(user_id));

        UserProfile userProfile = mongoTemplate.findOne(query, UserProfile.class);

        return userProfile.getCodingLanguages();
    }
    
}
