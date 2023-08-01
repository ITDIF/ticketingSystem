package com.jie.controller;

import com.alibaba.fastjson2.JSON;
import com.jie.pojo.Ticket;
import com.jie.service.TicketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jie
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Resource
    TicketService ticketService;

    @RequestMapping("/queryRemainingTicket")
    public int queryRemainingTicket(String route_number, String route_date){
        return ticketService.queryRemainingTicket(route_number,route_date);
    }
    @RequestMapping("/queryTicket")
    public List<Ticket> queryTicket(String date, String account, String start, String end){
        return ticketService.queryTicketByIdNumber(date,account,start, end);
    }
    @RequestMapping("/queryTicketListPaging")
    public List<Ticket> queryTicketListPaging(String start, String count, String key, String value){
        return ticketService.queryTicketListPaging(start,count,key,value);
    }
    @RequestMapping("/queryTicketCount")
    public int queryTicketCount(String key, String value){
        return ticketService.queryTicketCount(key, value);
    }
    @RequestMapping("/add")
    public int addTicket(String data, String date) {
        return ticketService.addTicket(date,JSON.parseObject(data, Ticket.class));
    }
    @RequestMapping("/update")
    public int update(String data, String date){
        return ticketService.updateTicket(JSON.parseObject(data, Ticket.class),date);
    }
    @RequestMapping("/del")
    public int deleteTicketByOrderNumber(String date, String order_number) {
        return ticketService.deleteTicketByOrderNumber(date,order_number);
    }
}
