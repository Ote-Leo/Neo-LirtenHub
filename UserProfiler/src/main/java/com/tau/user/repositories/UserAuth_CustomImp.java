package com.tau.user.repositories;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Component;

import com.tau.user.models.UserAuth;

@Component
public class UserAuth_CustomImp implements UserAuth_Custom{
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public void updatelogoutTime(long user_id, LocalDateTime logout) {
        Query query = new Query(Criteria.where("user_id").is(user_id));
        Update update = new Update();
        long maxid = -1;
        int recordNumofMaxId = 0;
        List<UserAuth> UserAuth = mongoTemplate.find(query, UserAuth.class);
        
        System.out.println("UserAuth.size()"+UserAuth.size());
        for(int i=0;i<UserAuth.size();i++){
            if(maxid<UserAuth.get(i).getId()){
                maxid =UserAuth.get(i).getId();
                recordNumofMaxId=i;
            }
        }

        Query query1 = new Query(Criteria.where("id").is(maxid));
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("GMT+02:00"));

        if(UserAuth.get(recordNumofMaxId).getLogoutTime()==null){
            update.set("logoutTime",localDateTime );
            mongoTemplate.updateFirst(query1, update, UserAuth.class);
        }
    }

    @Override
    public ArrayList<UserAuth> getUsers(long user_id) {

        Query query = new Query(Criteria.where("user_id").is(user_id));

        ArrayList<UserAuth> UserAuth = (ArrayList<UserAuth>) mongoTemplate.find(query, UserAuth.class);


        return UserAuth;
    }

}