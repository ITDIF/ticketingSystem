package com.jie.Test;

import com.jie.mapper.TicketMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTest {
    @Autowired
    TicketMapper ticketMapper;
    @Test
    public void Test(){
        System.out.println(ticketMapper.queryRemainingTicket("tt0001","2023-05-18"));
    }
    @Test
    public void ticket(){
        System.out.println(ticketMapper.insertRemainingTicketInfo("tt0001","40","2023-05-18"));
    }
}
