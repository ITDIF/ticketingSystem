package com.jie.controller;

import com.jie.config.RabbitMqConfig;
import com.jie.service.Impl.RabbitService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author jie
 */
@RestController
@RequestMapping("/rabbitMq")
public class SendMessageController {

    //使用RabbitTemplate,这提供了接收/发送等等方法
    @Resource
    RabbitTemplate rabbitTemplate;
    @Resource
    RabbitService rabbitService;

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    @RequestMapping("/sendDead")
    public void sendMsg() {
        String msg = "this is a test";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息发送时间：" + sdf.format(new Date()));
        rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_EXCHANGE_NAME, RabbitMqConfig.DELAY_ROUTING_KEY, msg, message -> {
            //消息延迟5秒
            message.getMessageProperties().setHeader("x-delay", 5000);
            return message;
        });
    }

    @RequestMapping("/cancelOrder")
    @ResponseBody
    public int cancelOrder(String order_number, String date){
        rabbitService.cancelOrder(order_number, date);
        return 1;
    }

}
