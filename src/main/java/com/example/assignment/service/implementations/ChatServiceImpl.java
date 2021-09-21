package com.example.assignment.service.implementations;

import com.example.assignment.model.ChatLog;
import com.example.assignment.model.Log;
import com.example.assignment.repo.interfaces.ChatRepo;
import com.example.assignment.service.interfaces.ChatService;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {

    ChatRepo chatRepo;

    public ChatServiceImpl(ChatRepo chatRepo) {
        this.chatRepo = chatRepo;
    }

    @Override
    public ChatLog addLog(ChatLog chatLog) {
        chatLog.getLog().get(0).setTimeStamp(new Date());
        int id = (int) (Math.random() * 10000);
        chatLog.getLog().get(0).setId(id);
        return chatRepo.addLog(chatLog);
    }

    @Override
    public List<Log> getLog(String userName) {
        Query query1 = new Query().addCriteria(Criteria.where("userName").is(userName)).with(Sort.by(Sort.Direction.ASC, "timeStamp"));

        ChatLog userChatLog = chatRepo.getLog(query1, userName);
        ArrayList<Log> userLog = userChatLog.getLog();
        return userLog;
    }

    @Override
    public String deleteLog(String userName) {
        Query query = new Query().addCriteria(Criteria.where("userName").is(userName));
         chatRepo.deleteLog(query);
         return userName + " chat is deleted";
    }

    @Override
    public String updateLog(ChatLog chatLog, String userName) {
        Query query = new Query().addCriteria(Criteria.where("userName").is(userName));
        chatLog.getLog().get(0).setTimeStamp(new Date());
        int id = (int) (Math.random() * 10000);
        chatLog.getLog().get(0).setId(id);
        chatRepo.updateLog(query, chatLog);
        return userName + " Updated";
    }

    @Override
    public String deleteSingleMsg(String userName, Integer id) {
        Query query = new Query().addCriteria(Criteria.where("userName").is(userName));
        ChatLog userChatLog = chatRepo.deleteSingleMsg(query);
        List<Log> userLog = userChatLog.getLog();
        int index = 0;
        for(int i = 0; i < userLog.size(); i++) {
            int temp = userLog.get(i).getId();
            if(temp == id) {
                index = i;
                break;
            }
        }
        userLog.remove(index);
        chatRepo.addLog(userChatLog);
        return userName + " message with id " + id + " is deleted";
    }

}
