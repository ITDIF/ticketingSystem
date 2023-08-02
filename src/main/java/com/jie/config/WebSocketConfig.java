package com.jie.config;

import com.jie.service.UserService;
import com.jie.webSocket.ManualCustomerWebSocket;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.Resource;

/**
 * @author jie
 */

@Configurable
//@Configuration
@Component
public class WebSocketConfig {
    /**
     * 	注入ServerEndpointExporter，
     * 	这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Resource
    public void setUserService(UserService userService) {
        ManualCustomerWebSocket.userService = userService;
    }
    @Resource
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        ManualCustomerWebSocket.redisTemplate = redisTemplate;
    }
}
