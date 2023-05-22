package com.jie.Test;

import com.jie.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicketServiceTest {
    @Autowired
    TicketService ticketService;

    @Test
    public void tableIsTrue(){
        System.out.println(ticketService.tableIsTrue("ticket_u"));
    }
    @Test
    public void createTableIsNotExist(){
        System.out.println(ticketService.createTableIsNotExist("ticket_c"));
    }
    @Test
    public void queryRemainingTicket(){
        System.out.println(ticketService.queryRemainingTicket("KK0001","2023-05-21"));
    }

}
