package com.jie.listener;

import com.jie.mapper.OrderMapper;
import com.jie.mapper.UserMapper;
import com.jie.pojo.Order;
import com.jie.pojo.OrderTemporary;
import com.jie.service.Impl.MailService;
import com.jie.service.Impl.RabbitService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Map;

/**
 * @author jie
 */
@Component
//监听的队列名称 TicketSuccessQueue
@RabbitListener(queues = "TicketSuccessQueue")
public class TicketSuccess {
    @Resource
    MailService mailService;
    @Resource
    UserMapper userMapper;
    @Resource
    OrderMapper orderMapper;
    @Resource
    RabbitService rabbitService;
    @RabbitHandler
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public void process(Map map) {
        System.out.println("TicketSuccessQueue消费者收到消息  : " + map.toString());
        String orderNumber = (String) map.get("orderNumber");
        OrderTemporary orderTemporary = orderMapper.queryOrderTemporary(orderNumber);
        Order order = new Order(null,String.valueOf(System.currentTimeMillis()),orderTemporary.getUsername(),orderTemporary.getRoute_number(),
                orderTemporary.getId_number(),orderTemporary.getDeparture_time(),orderTemporary.getFrom_station(),orderTemporary.getTo_station(),
                orderTemporary.getSeat_type(),orderTemporary.getSeat_id(),orderTemporary.getPrice(),orderTemporary.getOrder_time(),
                "已付款",new Timestamp(System.currentTimeMillis()));
        orderMapper.deleteOrderTemporaryByOrderNumber(orderNumber);
        orderMapper.addOrder(order);
        String QQ = userMapper.queryQQByIdNumber(orderTemporary.getId_number());
        mailService.ticketSuccessInform(QQ,order.getFrom_station(),order.getTo_station(),order.getDeparture_time().toString());
    }

}
