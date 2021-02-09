package com.seata.user.controller;

import com.seata.user.service.ScoketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/websocket")
public class IndexController {

    @Autowired
    private ScoketClient webScoketClient;

    @GetMapping("/sendMessage")
    public String sendMessage(String message){
        webScoketClient.groupSending(message);
        return message;
    }

}
