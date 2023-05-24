package com.jie.Test;

import com.jie.mapper.TicketMapper;
import com.jie.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TicketServiceTest {
    @Resource
    TicketService ticketService;

    TicketMapper ticketMapper;

    @Test
    public void tableIsTrue(){
        System.out.println(ticketService.tableIsTrue("ticket_u"));
    }
    @Test
    public void createTableIsNotExist(){
        System.out.println(ticketService.createTableIsNotExist("ticket_20230521"));
    }
    @Test
    public void queryRemainingTicket(){
        System.out.println(ticketService.queryRemainingTicket("KK0001","2023-05-21"));
    }

}
