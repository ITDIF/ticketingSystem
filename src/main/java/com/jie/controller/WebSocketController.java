package com.jie.controller;

import com.jie.service.Impl.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author jie
 */
@RestController
@RequestMapping("/webSocket")
public class WebSocketController {
    @Autowired
    WebSocket webSocket;
    @PostMapping("/sentMessage")
    public void sentMessage(String userId,String message){
        webSocket.sendOneMessage(userId,message);
    }

}

