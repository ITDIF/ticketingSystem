package com.jie.controller;

import com.jie.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author jie
 */
@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Resource
    TicketService ticketService;

    @RequestMapping("/queryRemainingTicket")
    @ResponseBody
    public int queryRemainingTicket(String route_number, String route_date){
        return ticketService.queryRemainingTicket(route_number,route_date);
    }
}
