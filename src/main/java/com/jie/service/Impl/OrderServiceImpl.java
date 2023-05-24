package com.jie.service.Impl;

import com.jie.mapper.*;
import com.jie.pojo.CarRoute;
import com.jie.pojo.OrderTemporary;
import com.jie.pojo.Ticket;
import com.jie.pojo.User;
import com.jie.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author jie
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    TicketMapper ticketMapper;
    @Resource
    OrderMapper orderMapper;
    @Resource
    CarRouteMapper carRouteMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    CarMapper carMapper;

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public String addOrderTemporary(String route_number, String route_date, String account) {
        System.out.println("addOrderTemporary: "+route_number+" "+route_date+" "+account);
        String result = ticketMapper.queryRemainingTicket(route_number, route_date);
        int remainingTicket = 0;
        if(result == null){
            //该行不存在，先生成该行
            remainingTicket = ticketMapper.queryPassengerCapacityByRouteNumber(route_number);
            ticketMapper.insertRemainingTicketInfo(route_number,String.valueOf(remainingTicket-1),route_date);
        }else if("0".equals(result)){
            //没有余票，无法下单
            return "-1";
        } else{
            remainingTicket = Integer.parseInt(result) - 1;
            ticketMapper.updateRemainingTicket(String.valueOf(remainingTicket),route_number,route_date);
        }
        String ticketTable = "ticket_"+route_date.replaceAll("-","");
        //查询该车次当天已购车票位置集合
        System.out.println("ticketTable: "+ticketTable);
        ticketMapper.createTableIsNotExist(ticketTable);
        List<Integer> list = ticketMapper.querySeat(ticketTable,route_number);
        int seat = 0, max = 100;
        for(int i = 1; i < max; i++){
            if(i > list.size() || list.get(i-1) != i){
                seat = i;
                break;
            }
        }
        CarRoute carRoute = carRouteMapper.queryCarRouteByRouteNumber(route_number);
        User user = userMapper.queryUserByAccount(account);
        String orderId = String.valueOf(System.currentTimeMillis());
        String seatType = carMapper.querySeatByCarNumber(carRoute.getCar_number());
        OrderTemporary orderTemporary = new OrderTemporary(null,orderId,user.getUsername(),route_number,user.getId_number(),
                new Timestamp(System.currentTimeMillis()),carRoute.getFrom_station(),carRoute.getTo_station(),seatType,seat,carRoute.getPrice(),
                new Timestamp(System.currentTimeMillis()),null);
        orderMapper.addOrderTemporary(orderTemporary);
        Ticket ticket = new Ticket(null,orderId,route_number,user.getUsername(),user.getId_number(),carRoute.getFrom_station(),carRoute.getTo_station(),
                seatType,seat,carRoute.getPrice(),new Timestamp(System.currentTimeMillis()));
        ticketMapper.addTicket(ticketTable,ticket);
        return orderId;
    }

    @Override
    public String queryOrderTimeByOrderNumber(String order_number) {
        return orderMapper.queryOrderTimeByOrderNumber(order_number);
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public int deleteOrderTemporaryAndTicket(String order_number, String date) {
        String table = "ticket_"+date.replaceAll("-","");
        String route_number = orderMapper.queryRouteNumberByOrderNumber(order_number);
        String result = ticketMapper.queryRemainingTicket(route_number, date);
        int remainingTicket = Integer.parseInt(result);
        ticketMapper.updateRemainingTicket(String.valueOf(remainingTicket+1),route_number,date);
        return orderMapper.deleteOrderTemporaryByOrderNumber(order_number) |
                ticketMapper.deleteTicketByOrderNumber(table,order_number);
    }
}
