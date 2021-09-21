package com.example.assignment.repo.interfaces;

import com.example.assignment.model.ChatLog;
import com.example.assignment.model.Log;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;


public interface ChatRepo {

    ChatLog addLog(ChatLog chatLog);

    ChatLog getLog(Query query, String userName);

    List<Log> sortBy(Query query);

    void deleteLog(Query query);

    void updateLog(Query query, ChatLog chatLog);

    ChatLog deleteSingleMsg(Query query);
}
