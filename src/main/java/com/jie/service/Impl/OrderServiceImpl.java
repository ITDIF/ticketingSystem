package com.jie.service.Impl;

import com.jie.mapper.*;
import com.jie.pojo.*;
import com.jie.service.CandidateService;
import com.jie.service.OrderService;
import com.jie.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @Resource
    MailService mailService;
    @Resource
    UserService userService;
    @Resource
    RabbitService rabbitService;
    @Resource
    CandidateMapper candidateMapper;
    @Resource
    CandidateService candidateService;
    @Resource
    RedisTemplate redisTemplate;

    @Override
    public List<Order> queryOrderListPaging(String start, String count,String key, String value) {
        return orderMapper.queryOrderListPaging(start,count,key,value);
    }

    @Override
    public int queryOrderCount(String key, String value) {
        return orderMapper.queryOrderCount(key,value);
    }

    @Override
    public Order queryOrderByOrderNumber(String orderNumber) {
        return orderMapper.queryOrder(orderNumber);
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class, IllegalArgumentException.class})
    public String addOrderTemporary(String route_number, String route_date, String account, String oldOrderNumber) {
        String status = "";
        System.out.println("00000000000000000"+oldOrderNumber);
        if(oldOrderNumber == null){
            status = "未付款";
        }else{
            status = "未付款(改签)";
        }
        System.out.println("addOrderTemporary: "+route_number+" "+route_date+" "+account);
        String result = ticketMapper.queryRemainingTicket(route_number, route_date);
        int remainingTicket = 0;
        if(result == null){
            //该行不存在，先生成该行数据
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
        //查询该车次当天已购车票位置集合，给用户分配座位（最小无人位置）
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
        String departureTime = route_date+" "+carRoute.getDeparture_time()+":00";
        OrderTemporary orderTemporary = new OrderTemporary(null,orderId,user.getUsername(),route_number,user.getId_number(),
                Timestamp.valueOf(departureTime),carRoute.getFrom_station(),carRoute.getTo_station(),seatType,seat,carRoute.getPrice(),
                new Timestamp(System.currentTimeMillis()),status);
        orderMapper.addOrderTemporary(orderTemporary);
        Ticket ticket = new Ticket(null,orderId,route_number,user.getUsername(),user.getId_number(),Timestamp.valueOf(departureTime),carRoute.getFrom_station(),carRoute.getTo_station(),
                seatType,seat,carRoute.getPrice(),new Timestamp(System.currentTimeMillis()));
        ticketMapper.addTicket(ticketTable,ticket);
        if(oldOrderNumber != null){
            redisTemplate.opsForValue().set("toRebook"+orderId, oldOrderNumber,600, TimeUnit.SECONDS);
        }
        return orderId;
    }

    @Override
    public int queryExistCandidateCount(String route_number, String departure_time) {
        return candidateMapper.queryExistCandidateCount(route_number, departure_time);
    }

    @Override
    public String queryOrderTimeByOrderNumber(String order_number) {
        return orderMapper.queryOrderTimeByOrderNumber(order_number);
    }

    @Override
    public List<OrderTemporary> queryOrderTemporaryByAccount(String account) {
        return orderMapper.queryOrderTemporaryByAccount(account);
    }

    @Override
    public List<Order> queryHistoricalOrderByAccount(String account) {
        return orderMapper.queryHistoricalOrderByAccount(account);
    }

    @Override
    public List<Order> queryHistoricalOrderConditional(String account, String startDate, String endDate, String key, String start, String count) {
        return orderMapper.queryHistoricalOrderConditionalPaging(account,startDate,endDate,key,start,count);
    }

    @Override
    public int queryHistoricalOrderConditionalCount(String account, String startDate, String endDate, String key) {
        return orderMapper.queryHistoricalOrderConditionalCount(account,startDate,endDate,key);
    }

    @Override
    public List<Order> queryNotTravelOrderByPaging(String account, String start, String count) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String departure_time = sdf.format(System.currentTimeMillis());
        return orderMapper.queryNotTravelOrderByPaging(account, departure_time, start, count);
    }

    @Override
    public List<Order> queryHistoricalOrderPaging(String account, String start, String count) {
        return orderMapper.queryHistoricalOrderPaging(account,start,count);
    }

    @Override
    public int queryHistoricalOrderCount(String account) {
        return orderMapper.queryHistoricalOrderCount(account);
    }

    @Override
    public int queryNotTravelOrderCount(String account) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String departure_time = sdf.format(System.currentTimeMillis());
        return orderMapper.queryNotTravelOrderCount(account, departure_time);
    }

    @Override
    public Map<String, Object> queryOrderTimeAndSeatByOrderNumber(String order_number) {
        return orderMapper.queryOrderTimeAndSeatByOrderNumber(order_number);
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public int addOrderAndDelTemporary(String orderNumber, String account) {
        OrderTemporary orderTemporary = orderMapper.queryOrderTemporary(orderNumber);
        Order order = new Order(null,orderNumber,orderTemporary.getUsername(),orderTemporary.getRoute_number(),
                orderTemporary.getId_number(),orderTemporary.getDeparture_time(),orderTemporary.getFrom_station(),orderTemporary.getTo_station(),
                orderTemporary.getSeat_type(),orderTemporary.getSeat_id(),orderTemporary.getPrice(),orderTemporary.getOrder_time(),
                "已付款",new Timestamp(System.currentTimeMillis()));
        userService.updateMoneyAndIntegral(account,order.getPrice().negate(),order.getPrice().intValue()*100);
        rabbitService.sendDirectMessageTicket(orderNumber);
        return orderMapper.deleteOrderTemporaryByOrderNumber(orderNumber) &
                orderMapper.addOrder(order);
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public int addOrderAndDelTemporaryAndUpOldOrder(String orderNumber, String oldOrderNumber, String account) {
        OrderTemporary orderTemporary = orderMapper.queryOrderTemporary(orderNumber);
        Order order = new Order(null,orderNumber,orderTemporary.getUsername(),orderTemporary.getRoute_number(),
                orderTemporary.getId_number(),orderTemporary.getDeparture_time(),orderTemporary.getFrom_station(),orderTemporary.getTo_station(),
                orderTemporary.getSeat_type(),orderTemporary.getSeat_id(),orderTemporary.getPrice(),orderTemporary.getOrder_time(),
                "已付款",new Timestamp(System.currentTimeMillis()));
        Order oldOrder = orderMapper.queryOrder(oldOrderNumber);
        userService.updateMoneyAndIntegral(account,oldOrder.getPrice().subtract(order.getPrice()),order.getPrice().intValue()*100-oldOrder.getPrice().intValue()*100);
        String date = oldOrder.getDeparture_time().toString().substring(0,10).replaceAll("-","");
        String table = "ticket_"+date;
        Map<String, Object> map = ticketMapper.queryRemainingTicketAndRouteNumber(oldOrderNumber,date);
        int remainingTicket = (int) map.get("remaining_tickets");
        ticketMapper.updateRemainingTicket(String.valueOf(remainingTicket+1), (String) map.get("route_number"),date);
        int result =  ticketMapper.deleteTicketByOrderNumber(table,oldOrderNumber) &
                orderMapper.updateOrder(new Order(null,oldOrderNumber,null,null,
                        null,null,null,null,null,null,null,null,"已改签",null));
        int result2 = orderMapper.deleteOrderTemporaryByOrderNumber(orderNumber) &
                orderMapper.addOrder(order) & result;
        candidateService.candidateSuccess(oldOrder.getRoute_number(), oldOrder.getDeparture_time().toString());
        return result2;
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public int candidateSuccess(String orderNumber, String account) {
        OrderTemporary orderTemporary = orderMapper.queryOrderTemporary(orderNumber);
        Order order = new Order(null,orderNumber,orderTemporary.getUsername(),orderTemporary.getRoute_number(),
                orderTemporary.getId_number(),orderTemporary.getDeparture_time(),orderTemporary.getFrom_station(),orderTemporary.getTo_station(),
                orderTemporary.getSeat_type(),orderTemporary.getSeat_id(),orderTemporary.getPrice(),orderTemporary.getOrder_time(),
                "待兑现(候补)",new Timestamp(System.currentTimeMillis()));
        userService.updateMoneyAndIntegral(account,order.getPrice().negate(),order.getPrice().intValue()*100);
        candidateMapper.updateCandidate(new Candidate(null,null,orderNumber, null,null,null,null,"待兑现"));
        int result =  orderMapper.deleteOrderTemporaryByOrderNumber(orderNumber) & orderMapper.addOrder(order);
        rabbitService.sendDirectMessageCandidate(orderNumber);
        return result;
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public String addCandidate(String route_number, String route_date, String account, int deadline) {
        User user = userMapper.queryUserByAccount(account);
        String orderNumber = String.valueOf(System.currentTimeMillis());
        CarRoute carRoute = carRouteMapper.queryCarRouteByRouteNumber(route_number);
        String departureTime = route_date+" "+carRoute.getDeparture_time()+":00";
        Candidate candidate = new Candidate(null,route_number,orderNumber,user.getId_number(),
                Timestamp.valueOf(departureTime), new Timestamp(System.currentTimeMillis()), deadline,"待支付");
        candidateMapper.addCandidate(candidate);
        OrderTemporary orderTemporary = new OrderTemporary(null,orderNumber,user.getUsername(),route_number,user.getId_number(),
                Timestamp.valueOf(departureTime),carRoute.getFrom_station(),carRoute.getTo_station(),null,null,carRoute.getPrice(),
                new Timestamp(System.currentTimeMillis()),"未付款(候补)");
        orderMapper.addOrderTemporary(orderTemporary);
        return orderNumber;
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public int deleteOrderTemporaryAndTicket(String order_number, String date) {
        String table = "ticket_"+date.replaceAll("-","");
        if(orderMapper.queryOrderByOrderNumber(order_number) == 0){
            return 1;
        }
        String route_number = orderMapper.queryRouteNumberByOrderNumber(order_number);
        String result = ticketMapper.queryRemainingTicket(route_number, date);
        int remainingTicket = Integer.parseInt(result);
        ticketMapper.updateRemainingTicket(String.valueOf(remainingTicket+1),route_number,date);
        ticketMapper.deleteTicketByOrderNumber(table,order_number);
        OrderTemporary orderTemporary = orderMapper.queryOrderTemporary(order_number);
        candidateService.candidateSuccess(orderTemporary.getRoute_number(), orderTemporary.getDeparture_time().toString());
        return orderMapper.deleteOrderTemporaryByOrderNumber(order_number);
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public int upOrderAndDelTicket(String order_number, String date, String account) {
        String table = "ticket_"+date.replaceAll("-","");
        Map<String, Object> map = ticketMapper.queryRemainingTicketAndRouteNumber(order_number,date);
        int remainingTicket = (int) map.get("remaining_tickets");
        ticketMapper.updateRemainingTicket(String.valueOf(remainingTicket+1), (String) map.get("route_number"),date);
        int result =  ticketMapper.deleteTicketByOrderNumber(table,order_number) &
                orderMapper.updateOrder(new Order(null,order_number,null,null,
                        null,null,null,null,null,null,null,null,"已取消",null));
        Order order = orderMapper.queryOrder(order_number);
        userService.updateMoneyAndIntegral(account,order.getPrice(),-order.getPrice().intValue()*100);
        candidateService.candidateSuccess(order.getRoute_number(), order.getDeparture_time().toString());
        return result;
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public int upOrderAndTicket(Order order, String date) {
        String table = "ticket_"+date.replaceAll("-","");
        Ticket ticket = new Ticket(null,order.getOrder_number(),order.getRoute_number(),order.getUsername(),order.getId_number(),
                order.getDeparture_time(),order.getFrom_station(),order.getTo_station(),order.getSeat_type(),order.getSeat_id(),order.getPrice(),order.getOrder_time());
        return orderMapper.updateOrder(order) & ticketMapper.updateTicket(table,ticket);
    }

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public int deleteOrderTemporaryAndCandidate(String order_number) {
        if(orderMapper.queryOrderByOrderNumber(order_number) == 0){
            return 1;
        }
        return orderMapper.deleteOrderTemporaryByOrderNumber(order_number) &
                candidateMapper.deleteCandidateByOrderNumber(order_number);
    }

    @Override
    public int deleteOrderByOrderNumber(String order_number) {
        return orderMapper.deleteOrderByOrderNumber(order_number);
    }

    @Override
    public int batchDelByOrderNumber(List<String> orderNumbers) {
        return orderMapper.batchDelByOrderNumber(orderNumbers);
    }
}
