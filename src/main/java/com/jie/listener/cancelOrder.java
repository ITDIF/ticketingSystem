package com.jie.listener;

import com.jie.config.RabbitMqConfig;
import com.jie.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * @author jie
 */
@Component
public class cancelOrder {
    @Resource
    OrderService orderService;
    @RabbitListener(queues = RabbitMqConfig.DELAY_QUEUE_NAME)
    public void receive(Map map) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date())+"订单超时 "+map.get("orderNumber"));
        orderService.deleteOrderTemporaryAndTicket((String) map.get("orderNumber"), (String) map.get("date"));
    }
}
