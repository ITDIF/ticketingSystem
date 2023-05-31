package com.jie.service.Impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
@Component
public class RabbitService {
    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 出票
     * @return
     */
    public String sendDirectMessageTicket(String orderNumber) {
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("createTime",createTime);
        map.put("orderNumber", orderNumber);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TicketSuccessExchange", "TicketSuccess", map);
        return "ok";
    }
}
