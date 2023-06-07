package com.jie.service.Impl;

import com.jie.config.RabbitMqConfig;
import com.jie.mapper.CandidateMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;
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
    @Resource
    CandidateMapper candidateMapper;

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
    public String sendDirectMessageCandidate(String orderNumber) {
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("createTime",createTime);
        map.put("orderNumber", orderNumber);
        rabbitTemplate.convertAndSend("TicketSuccessExchange", "candidate_success_key", map);
        return "ok";
    }
    public int cancelOrder(String order_number, String date) {
        Map<String,Object> map=new HashMap<>();
        map.put("date",date);
        map.put("orderNumber", order_number);
        rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_EXCHANGE_NAME, RabbitMqConfig.DELAY_ROUTING_KEY, map, message -> {
            //消息延迟10分钟
            message.getMessageProperties().setHeader("x-delay", 1000*60*10+10);
            return message;
        });
        return 1;
    }
    public int cancelCandidateOrder(String order_number) {
        Map<String,Object> map=new HashMap<>();
        map.put("order_number",order_number);
        map.put("op",1);
        rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_EXCHANGE_NAME, "candidate_queue_key", map, message -> {
            //消息延迟10分钟
            message.getMessageProperties().setHeader("x-delay", 1000*60*10+10);
            return message;
        });
        return 1;
    }
    public int deadlineCandidate(String order_number, Timestamp departure_time){
        int deadline = candidateMapper.queryDeadlineByOrderNumber(order_number);
        System.out.println("this is a deadlineCandidate");
        Map<String,Object> map=new HashMap<>();
        map.put("order_number",order_number);
        map.put("op",2);
        long time = departure_time.getTime()-deadline*3600*1000-System.currentTimeMillis();
        rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_EXCHANGE_NAME, "candidate_queue_key", map, message -> {
            message.getMessageProperties().setHeader("x-delay", time);
            return message;
        });
        return 1;
    }

}
