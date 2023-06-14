package com.jie.controller;

import com.jie.pojo.Order;
import com.jie.pojo.OrderTemporary;
import com.jie.service.Impl.RabbitService;
import com.jie.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author jie
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderService orderService;
    @Resource
    RabbitService rabbitService;

    @RequestMapping("/temporary")
    @ResponseBody
    public String temporary(String route_number, String route_date, String account){
        String orderId = orderService.addOrderTemporary(route_number,route_date,account);
        if(!"-1".equals(orderId)){
            rabbitService.cancelOrder(orderId,route_date);
        }
        return orderId;
    }
    @RequestMapping("/queryOrderTimeByOrderNumber")
    @ResponseBody
    public String queryOrderTimeByOrderNumber(String order_number){
        return orderService.queryOrderTimeByOrderNumber(order_number);
    }
    @RequestMapping("/queryOrderTimeAndSeatByOrderNumber")
    @ResponseBody
    public Map<String,Object> queryOrderTimeAndSeat(String order_number){
        return orderService.queryOrderTimeAndSeatByOrderNumber(order_number);
    }
    @RequestMapping("/queryOrderTemporaryByAccount")
    @ResponseBody
    public List<OrderTemporary> queryOrderTemporaryByAccount(String account){
        return orderService.queryOrderTemporaryByAccount(account);
    }
    @RequestMapping("/deleteOrderTemporaryAndTicket")
    @ResponseBody
    public int deleteOrderTemporaryAndTicket(String order_number, String date){
        return orderService.deleteOrderTemporaryAndTicket(order_number,date);
    }
    @RequestMapping("/addOrderAndDelTemporary")
    @ResponseBody
    public int addOrderAndDelTemporary(String order_number){
        return orderService.addOrderAndDelTemporary(order_number);
    }
    @RequestMapping("/candidateSuccess")
    @ResponseBody
    public int candidateSuccess(String order_number){
        return orderService.candidateSuccess(order_number);
    }
    @RequestMapping("/queryHistoricalOrderByAccount")
    @ResponseBody
    public List<Order> queryHistoricalOrderByAccount(String account){
        return orderService.queryHistoricalOrderByAccount(account);
    }
    @RequestMapping("/queryHistoricalOrderPaging")
    @ResponseBody
    public List<Order> queryHistoricalOrderPaging(String account, String start, String count){
        return orderService.queryHistoricalOrderPaging(account,start,count);
    }
    @RequestMapping("/queryHistoricalOrderCount")
    @ResponseBody
    public int queryHistoricalOrderCount(String account){
        return orderService.queryHistoricalOrderCount(account);
    }
    @RequestMapping("/candidate")
    @ResponseBody
    public String candidate(String route_number, String route_date, String account,int deadline){
        String orderNumber = orderService.addCandidate(route_number, route_date, account, deadline);
        rabbitService.cancelCandidateOrder(orderNumber);
        return orderNumber;
    }
    @RequestMapping("/existCandidate")
    @ResponseBody
    public int existCandidate(String route_number, String departure_time){
        return orderService.queryExistCandidateCount(route_number,departure_time);
    }
    @RequestMapping("/deleteOrderTemporaryAndCandidate")
    @ResponseBody
    public int deleteOrderTemporaryAndCandidate(String order_number){
        return orderService.deleteOrderTemporaryAndCandidate(order_number);
    }

}
