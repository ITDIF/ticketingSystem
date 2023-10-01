package com.jie.listener;

import com.jie.mapper.*;
import com.jie.pojo.CarRoute;
import com.jie.pojo.Order;
import com.jie.pojo.Ticket;
import com.jie.service.Impl.MailService;
import com.jie.service.Impl.RabbitService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author jie
 */
@Component
public class TicketSuccess {
    @Resource
    MailService mailService;
    @Resource
    UserMapper userMapper;
    @Resource
    OrderMapper orderMapper;
    @Resource
    RabbitService rabbitService;
    @Resource
    CandidateMapper candidateMapper;
    @Resource
    TicketMapper ticketMapper;
    @Resource
    CarRouteMapper carRouteMapper;
    @Resource
    CarMapper carMapper;
    @RabbitListener(queues = "TicketSuccessQueue")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public void process(Map map) {
        System.out.println("TicketSuccessQueue消费者收到消息  : " + map.toString());
        String orderNumber = (String) map.get("orderNumber");
        Order order = orderMapper.queryOrder(orderNumber);
        String qq = userMapper.queryQQByIdNumber(order.getId_number());
        try {
            mailService.ticketSuccessInform(qq,order.getFrom_station(),order.getTo_station(),order.getDeparture_time().toString());
        }catch (Exception e){
            System.out.println("短信发送失败！");
        }
    }
    @RabbitListener(queues = "CandidateSuccessQueue")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public void candidate(Map map) {
        System.out.println("CandidateSuccessQueue消费者收到消息  : " + map.toString());
        String orderNumber = (String) map.get("orderNumber");
        Order order = orderMapper.queryOrder(orderNumber);
        rabbitService.deadlineCandidate(orderNumber,order.getDeparture_time());
        String QQ = userMapper.queryQQByIdNumber(order.getId_number());
        try {
            mailService.candidateSuccessInform(QQ,order.getFrom_station(),order.getTo_station(),order.getDeparture_time().toString());
        }catch (Exception e){
            System.out.println("短信发送失败！");
        }
    }
    @RabbitListener(queues = "WaitingSuccessQueue")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public void waiting(Map map) {
        System.out.println("WaitingSuccessQueue消费者收到消息  : " + map.toString());
        String routeNumber = (String) map.get("routeNumber");
        String departureTime = (String) map.get("departureTime");
        String orderNumber = candidateMapper.queryMinCandidate(routeNumber,departureTime);
        String routeDate = departureTime.substring(0,10);
        String result = ticketMapper.queryRemainingTicket(routeNumber, routeDate);
        System.out.println("候补"+routeNumber+" "+routeDate+"--------------"+result);
        if(orderNumber != null && !"0".equals(result)){
            int remainingTicket = Integer.parseInt(result) - 1;
            ticketMapper.updateRemainingTicket(String.valueOf(remainingTicket),routeNumber,routeDate);
            String ticketTable = "ticket_"+routeDate.replaceAll("-","");
            //查询该车次当天已购车票位置集合，给用户分配座位（最小无人位置）
            List<Integer> list = ticketMapper.querySeat(ticketTable,routeNumber);
            int seat = 0, max = 100;
            for(int i = 1; i < max; i++){
                if(i > list.size() || list.get(i-1) != i){
                    seat = i;
                    break;
                }
            }
            Order order = orderMapper.queryOrder(orderNumber);
            CarRoute carRoute = carRouteMapper.queryCarRouteByRouteNumber(routeNumber);
            String seatType = carMapper.querySeatByCarNumber(carRoute.getCar_number());
            Ticket ticket = new Ticket(null,orderNumber,routeNumber,order.getUsername(),order.getId_number(),order.getDeparture_time(),order.getFrom_station(),carRoute.getTo_station(),
                    seatType,seat,order.getPrice(),new Timestamp(System.currentTimeMillis()));
            ticketMapper.addTicket(ticketTable,ticket);
            candidateMapper.deleteCandidateByOrderNumber(orderNumber);
            orderMapper.updateOrder(new Order(null,orderNumber,null,null,null,null,null,null,seatType,seat,
                    null,null,"已付款(候补)",null));
            String QQ = userMapper.queryQQByIdNumber(order.getId_number());
            try {
                mailService.waitingSuccess(QQ,order.getFrom_station(),order.getTo_station(),order.getDeparture_time().toString());
            }catch (Exception e){
                System.out.println("短信发送失败！");
            }
        }
    }

}
