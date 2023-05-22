package com.jie.Test;

import com.jie.mapper.OrderMapper;
import com.jie.mapper.TicketMapper;
import com.jie.pojo.Order;
import com.jie.pojo.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;

@SpringBootTest
public class MapperTest {
    @Resource
    TicketMapper ticketMapper;

    @Resource
    OrderMapper orderMapper;
    @Test
    public void Test(){
        System.out.println(ticketMapper.queryRemainingTicket("tt0001","2023-05-18"));
    }
    @Test
    public void ticket(){
        System.out.println(ticketMapper.insertRemainingTicketInfo("tt0001","40","2023-05-18"));
    }
    @Test
    public void addTicket(){
        Ticket ticket = new Ticket(null,"5","2","23","1","1","2",1,new BigDecimal(Double.valueOf(10)),new Timestamp(System.currentTimeMillis()));
        String table = "ticket_a";
        try {
            System.out.println(ticketMapper.addTicket(table,ticket));
        }catch (Exception e){
            System.out.println("添加失败！");
        }
    }
    @Test
    public void addOrder(){
        Order order = new Order(null,"4","44","3","3",new Timestamp(System.currentTimeMillis()),"to","to","z",1,new BigDecimal(Double.valueOf(10)),new Timestamp(System.currentTimeMillis()),"s",new Timestamp(System.currentTimeMillis()));
        System.out.println(orderMapper.addOrder(order));
    }
}
