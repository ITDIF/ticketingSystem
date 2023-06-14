package com.jie.listener;

import com.jie.config.RabbitMqConfig;
import com.jie.mapper.CandidateMapper;
import com.jie.mapper.OrderMapper;
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
    @Resource
    CandidateMapper candidateMapper;
    @Resource
    OrderMapper orderMapper;
    @RabbitListener(queues = RabbitMqConfig.DELAY_QUEUE_NAME)
    public void receive(Map map) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date())+"订单超时 "+map.get("orderNumber"));
        orderService.deleteOrderTemporaryAndTicket((String) map.get("orderNumber"), (String) map.get("date"));
    }
    @RabbitListener(queues = "candidateQueue")
    public void candidateQueue(Map map){
        int op = (int) map.get("op");
        String order_number = (String) map.get("order_number");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(op == 1){
            System.out.println(sdf.format(new Date())+"候补订单超时 "+order_number);
            orderService.deleteOrderTemporaryAndCandidate(order_number);
        }else{
            System.out.println(sdf.format(new Date())+"候补订单未兑现 "+order_number);
            candidateMapper.deleteCandidateByOrderNumber(order_number);
            orderMapper.deleteOrderByOrderNumber(order_number);
        }

    }
}
