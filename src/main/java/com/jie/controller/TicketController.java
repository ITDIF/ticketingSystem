package com.jie.controller;

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
    public List<Ticket> queryTicket(String date, String account){
        return ticketService.queryTicketByIdNumber(date,account);
    }
}
