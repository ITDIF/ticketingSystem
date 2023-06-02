package com.jie.service.Impl;

import com.jie.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
/**
 * @author jie
 */
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
        rabbitTemplate.convertAndSend("TicketSuccessExchange", "TicketSuccess", map);
        return "ok";
    }
    public int cancelOrder(String order_number, String date) {
        Map<String,Object> map=new HashMap<>();
        map.put("date",date);
        map.put("orderNumber", order_number);
        rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_EXCHANGE_NAME, RabbitMqConfig.DELAY_ROUTING_KEY, map, message -> {
            //消息延迟10分钟
            message.getMessageProperties().setHeader("x-delay", 1000*60*10);
            return message;
        });
        return 1;
    }

}
