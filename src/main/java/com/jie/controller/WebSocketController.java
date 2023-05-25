package com.jie.controller;

import com.jie.service.Impl.WebSocket;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author jie
 */
@RestController
@RequestMapping("/webSocket")
public class WebSocketController {
    @Resource
    WebSocket webSocket;
    @RequestMapping("/sentMessage")
    public void sentMessage(String userId,String message){
        webSocket.sendOneMessage(userId,message);
    }
    @RequestMapping("/w2")
    public void sentMessage(){
        System.out.println("连接成功！");
    }


}

