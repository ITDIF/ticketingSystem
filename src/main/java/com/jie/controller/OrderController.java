package com.jie.controller;

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

    @RequestMapping("/temporary")
    @ResponseBody
    public String temporary(String route_number, String route_date, String account){

        return orderService.addOrderTemporary(route_number,route_date,account);
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

}
