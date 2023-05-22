package com.jie.controller;

import com.jie.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jie
 */
@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @RequestMapping("/queryRemainingTicket")
    @ResponseBody
    public int queryRemainingTicket(String route_number, String route_date){
        return ticketService.queryRemainingTicket(route_number,route_date);
    }
}
