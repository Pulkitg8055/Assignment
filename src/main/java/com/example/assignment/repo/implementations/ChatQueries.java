package com.example.assignment.repo.implementations;

import com.example.assignment.model.ChatLog;
import com.example.assignment.model.Log;
import com.example.assignment.repo.interfaces.ChatRepo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChatQueries implements ChatRepo {

    MongoTemplate mongoTemplate;

    public ChatQueries(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ChatLog addLog(ChatLog chatLog) {
        mongoTemplate.save(chatLog);
        return chatLog;
    }

    @Override
    public ChatLog getLog(Query query, String userName) {
        return mongoTemplate.findOne(query, ChatLog.class);

    }

    @Override
    public List<Log> sortBy(Query query) {
       return mongoTemplate.find(query, Log.class);
    }

    @Override
    public void deleteLog(Query query) {
        mongoTemplate.remove(query, ChatLog.class);
    }

    @Override
    public void updateLog(Query query, ChatLog chatLog) {
        ChatLog userChatLog = mongoTemplate.findOne(query, ChatLog.class);
        userChatLog.getLog().add(chatLog.getLog().get(0));
        mongoTemplate.save(userChatLog);
    }

    @Override
    public ChatLog deleteSingleMsg(Query query) {
        return mongoTemplate.findOne(query, ChatLog.class);
    }

}
