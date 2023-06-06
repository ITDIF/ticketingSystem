package com.jie.controller;

import com.jie.service.Impl.RabbitService;
import com.jie.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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

    @RequestMapping("/candidate")
    @ResponseBody
    public String candidate(String route_number, String route_date, String account){
        return orderService.addCandidate(route_number, route_date, account);
    }
    @RequestMapping("/existCandidate")
    @ResponseBody
    public int existCandidate(String route_number, String departure_time){
        return orderService.queryExistCandidateCount(route_number,departure_time);
    }

}
