package com.jie.Test;

import com.jie.mapper.CandidateMapper;
import com.jie.mapper.OrderMapper;
import com.jie.mapper.TicketMapper;
import com.jie.pojo.Order;
import com.jie.pojo.OrderTemporary;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
public class MapperTest {
    @Resource
    TicketMapper ticketMapper;

    @Resource
    OrderMapper orderMapper;
    @Resource
    CandidateMapper candidateMapper;
    @Test
    public void Test(){
        System.out.println(ticketMapper.queryRemainingTicket("tt0001","2023-07-01"));
    }
    @Test
    public void ticket(){
        System.out.println(ticketMapper.insertRemainingTicketInfo("tt0001","40","2023-05-18"));
    }
//    @Test
//    public void addTicket(){
//        Ticket ticket = new Ticket(null,"50","kkk","2","23","1","1","2",1,new BigDecimal(Double.valueOf(10)),new Timestamp(System.currentTimeMillis()));
//        String table = "ticket";
////        try {
////            System.out.println(ticketMapper.addTicket(table,ticket));
////        }catch (Exception e){
////            System.out.println("添加失败！");
////        }
//        System.out.println(ticketMapper.addTicket(table,ticket));
//    }
    @Test
    public void addOrder(){
        Order order = new Order(null,"3","44","3","3",new Timestamp(System.currentTimeMillis()),"to","to","z",1,new BigDecimal(Double.valueOf(10)),new Timestamp(System.currentTimeMillis()),"s",new Timestamp(System.currentTimeMillis()));
        System.out.println(orderMapper.addOrder(order));
    }
    @Test
    public void addOrderT(){
        OrderTemporary orderTemporary = new OrderTemporary(null,"1","44","3","3",new Timestamp(System.currentTimeMillis()),"to","to","z",1,new BigDecimal(Double.valueOf(10)),new Timestamp(System.currentTimeMillis()),"s");
        System.out.println(orderMapper.addOrderTemporary(orderTemporary));
    }
    @Test
    public void querySeat(){
        System.out.println(ticketMapper.querySeat("ticket_1","KK0001"));
    }
    @Test
    public void queryOrderTime(){
        System.out.println(orderMapper.queryOrderTimeByOrderNumber("1684832607019"));
    }
    @Test
    public void delCandidateAndOrderT(){
        String id = "1686108176739";
        System.out.println(candidateMapper.deleteCandidateByOrderNumber(id)
                | orderMapper.deleteOrderTemporaryByOrderNumber(id));
    }
    @Test
    public void queryOrderTimeAndSeat(){
        System.out.println(orderMapper.queryOrderTimeAndSeatByOrderNumber("1686533578981"));
    }
    @Test
    public void queryTicketCountByIdNumber(){
        List<String> tableNames = ticketMapper.queryTableName("ticket_20230701");
        System.out.println(tableNames);
        System.out.println(ticketMapper.queryTicketCountByIdNumber(tableNames,"1111111"));
        System.out.println(ticketMapper.queryTicketByIdNumber(tableNames,"1111111"));
    }

}
