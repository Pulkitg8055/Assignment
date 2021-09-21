package com.example.assignment.controllers;


import com.example.assignment.model.ChatLog;
import com.example.assignment.model.Log;
import com.example.assignment.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/chatlogs")
public class ChatController {

    @Autowired
    ChatService chatService;

    @GetMapping("/hi")
    public String get() {
        return "HI";
    }

    @GetMapping("/{userName}")
    public List<Log> getLog(@PathVariable String userName) {
        return chatService.getLog(userName);
    }

    @PostMapping("/")
    public ChatLog addLog(@RequestBody ChatLog chatLog) {
        return chatService.addLog(chatLog);
    }

    @PutMapping("/{userName}")
    public String updateLog(@PathVariable String userName, @RequestBody ChatLog chatLog) {
        return chatService.updateLog(chatLog, userName);
    }

    @DeleteMapping("/{userName}")
    public String deleteLog(@PathVariable String userName){
        return chatService.deleteLog(userName);
    }

    @DeleteMapping("/{userName}/{id}")
    public String deleteSingleMsg(@PathVariable String userName, @PathVariable Integer id) {
        return chatService.deleteSingleMsg(userName, id);
    }

}
