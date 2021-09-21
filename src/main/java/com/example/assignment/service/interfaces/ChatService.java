package com.example.assignment.service.interfaces;

import com.example.assignment.model.ChatLog;
import com.example.assignment.model.Log;

import java.util.List;


public interface ChatService {
    
    ChatLog addLog(ChatLog chatLog);

    List<Log> getLog(String userName);

    String deleteLog(String userName);

    String updateLog(ChatLog chatLog, String userName);

    String deleteSingleMsg(String userName, Integer id);
}
