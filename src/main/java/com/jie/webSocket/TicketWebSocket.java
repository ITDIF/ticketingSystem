package com.jie.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author jie
 */
@Component
@Slf4j
@ServerEndpoint("/ticket/{userId}")  // 接口路径 ws://localhost:8081/ticket/userId;

public class TicketWebSocket {

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 用户ID
     */
    private String userId;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    //虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
    /**
     * 注：底下WebSocket是当前类名
     */
    private static final CopyOnWriteArraySet<TicketWebSocket> webSockets =new CopyOnWriteArraySet<>();
    /**
     * 用来存在线连接用户信息
     */
    private static final ConcurrentHashMap<String,Session> sessionPool = new ConcurrentHashMap<String,Session>();

    /**
     * 链接成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value="userId")String userId) {

        try {
            this.session = session;
            this.userId = userId;
            webSockets.add(this);
            sessionPool.put(userId, session);
            log.info("【ticket消息】有新的连接，总数为:"+webSockets.size());
            System.out.println("【ticket消息】有新的连接，总数为:"+webSockets.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 链接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        try {
            webSockets.remove(this);
            sessionPool.remove(this.userId);
            log.info("【ticket消息】连接断开，总数为:"+webSockets.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 收到客户端消息后调用的方法
     *
     */
    @OnMessage
    public void onMessage(String message) throws InterruptedException {
        log.info("【ticketWebsocket消息】收到客户端消息:"+message);
        System.out.println("ticketWebsocket消息:"+message);
//        session.getAsyncRemote().sendText("来自服务端的消息！+_+ ");
        for(int i = 0; i < 100; i++){
            synchronized (session){
                session.getAsyncRemote().sendText("+_+ "+i);
                Thread.sleep(500);
            }

        }
    }

    /** 发送错误时的处理
     */
    @OnError
    public void onError(Session session, Throwable error) {

        log.error("用户错误,原因:"+error.getMessage());
        error.printStackTrace();
    }

}
