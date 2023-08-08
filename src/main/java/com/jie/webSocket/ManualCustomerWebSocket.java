package com.jie.webSocket;

import com.alibaba.fastjson2.JSON;
import com.jie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/8/1 14:51
 */
@Component
@Slf4j
@ServerEndpoint("/customerService/{account}")
public class ManualCustomerWebSocket {
    public static UserService userService;
    public static RedisTemplate redisTemplate;
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 用户ID
     */
    private String account;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    //虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
    /**
     * 注：底下WebSocket是当前类名
     */
    private static final CopyOnWriteArraySet<ManualCustomerWebSocket> webSockets = new CopyOnWriteArraySet<>();
    /**
     * 用来存在线连接用户信息
     */
    private static final ConcurrentHashMap<String,Session> sessionPool = new ConcurrentHashMap<String,Session>();

    /**
     * 链接成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value="account")String account) {
        try {
            this.session = session;
            this.account = account;
            webSockets.add(this);
            sessionPool.put(account, session);
            addToRedisOnline(account);
            log.info("【customerServiceWebsocket消息】有新的连接，总数为:"+webSockets.size());
            System.out.println("【customerServiceWebsocket消息】有新的连接，总数为:"+webSockets.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 链接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        removeAccount(this.account);
        try {
            webSockets.remove(this);
            sessionPool.remove(this.account);
            log.info("【customerServiceWebsocket消息】连接断开，总数为:"+webSockets.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 收到客户端消息后调用的方法
     *
     */
    @OnMessage
    public void onMessage(String message) throws InterruptedException, IOException {
        log.info("【customerServiceWebsocket消息】收到客户端消息:"+message);
        System.out.println("customerServiceWebsocket消息:"+message);
        if("ping".equals(message)){
            session.getAsyncRemote().sendText("pong");
            return;
        }
        // 点对点发送数据（给指定用户发送消息）
        Map<String, String> map = (Map<String, String>) JSON.parse(message);
        msgToRedis(map);
        Session toSession = sessionPool.get(map.get("toPeo"));
        if (toSession != null && toSession.isOpen()) {
            toSession.getBasicRemote().sendText(map.toString());
        }
    }

    /** 发送错误时的处理
     */
    @OnError
    public void onError(Session session, Throwable error) {

        log.error("用户错误,原因:"+error.getMessage());
        error.printStackTrace();
    }

    /**
     * 添加需要服务的账号到redis
     * @param account 账号
     */
    public void addToRedisOnline(String account){
        if(userService.queryAccount(account)){
            redisTemplate.opsForList().rightPush("userHelpOnline",account);
        }
    }

    /**
     * 移除需要服务的在线人
     * @param account 账号
     */
    public void removeAccount(String account){
        if(userService.queryAccount(account)){
            redisTemplate.opsForList().remove("userHelpOnline",0,account);
        }
    }

    public void msgToRedis(Map<String, String> map){
        String listName = "";
        if("user".equals(map.get("belong"))){
            listName = "message-"+map.get("account");
        }else{
            listName = "message-"+map.get("toPeo");
        }
        redisTemplate.opsForList().rightPush(listName,map);
    }

}
