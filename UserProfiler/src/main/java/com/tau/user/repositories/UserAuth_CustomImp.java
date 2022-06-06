package com.tau.user.repositories;

import java.time.LocalDateTime;
import java.util.List;

//import javax.management.Query;

import org.bson.codecs.jsr310.LocalDateTimeCodec;
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
        // TODO Auto-generated method stub
        Query query = new Query(Criteria.where("user_id").is(user_id));
        Update update = new Update();
        long maxid=-1;
        int recordNumofMaxId=-2;
        List<UserAuth> UserAuth = mongoTemplate.find(query, UserAuth.class);

        for(int i=0;i<UserAuth.size();i++){
            if(maxid<UserAuth.get(i).getId()){
                maxid =UserAuth.get(i).getId();
                recordNumofMaxId=i;
            }
        }

        Query query1 = new Query(Criteria.where("id").is(maxid));
        if(UserAuth.get(recordNumofMaxId).getLogoutTime()==null){
        update.set("logoutTime", new LocalDateTimeCodec());
        mongoTemplate.updateFirst(query1, update, UserAuth.class);
        }
    }

}