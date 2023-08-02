package com.jie.service.Impl;

import com.jie.mapper.TicketMapper;
import com.jie.mapper.UserMapper;
import com.jie.pojo.Ticket;
import com.jie.service.TicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jie
 */
@Service
public class TicketServiceImpl implements TicketService {
    @Resource
    TicketMapper ticketMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public List<Ticket> queryTicketListPaging(String start, String count, String key, String value) {
        List<String> tableNames = ticketMapper.queryAllTableName();
        if(tableNames.size() == 0) {
            return new ArrayList<Ticket>();
        }
        return ticketMapper.queryTicketListPaging(tableNames, start, count, key, value);
    }

    @Override
    public int queryTicketCount(String key, String value) {
        List<String> tableNames = ticketMapper.queryAllTableName();
        if(tableNames.size() == 0) {
            return 0;
        }
        return ticketMapper.queryTicketCount(tableNames, key, value);
    }

    @Override
    public String tableIsTrue(String table) {
        return ticketMapper.tableIsTrue(table);
    }

    @Override
    public int createTableIsNotExist(String table) {
        return ticketMapper.createTableIsNotExist(table);
    }

    @Override
    public int queryRemainingTicket(String route_number, String route_date) {
        String result = ticketMapper.queryRemainingTicket(route_number, route_date);
        int remainingTicket = 0;
        if(result == null){
            remainingTicket = ticketMapper.queryPassengerCapacityByRouteNumber(route_number);
            ticketMapper.insertRemainingTicketInfo(route_number,String.valueOf(remainingTicket),route_date);
        }else{
            remainingTicket = Integer.parseInt(result);
        }
        return remainingTicket;
    }

    @Override
    public List<Ticket> queryTicketByIdNumber(String date, String account, String start, String end) {
        List<String> tableNames = ticketMapper.queryTableName("ticket_"+date,start,end);
        if(tableNames.size() == 0) {
            return new ArrayList<Ticket>();
        }
        System.out.println("tableNames "+tableNames);
        String idNumber = userMapper.queryIdNumberByAccount(account);
        return ticketMapper.queryTicketByIdNumber(tableNames,idNumber, start, end);
    }

    @Override
    public List<Ticket> queryAllTicket() {
        List<String> tableNames = ticketMapper.queryAllTableName();
        return ticketMapper.queryAllTicket(tableNames);
    }

    @Override
    public int addTicket(String date, Ticket ticket) {
        String table = "ticket_"+date.replaceAll("-","");
        return ticketMapper.addTicket(table,ticket);
    }

    @Override
    public int updateTicket(Ticket ticket, String date) {
        String table = "ticket_"+date.replaceAll("-","");
        return ticketMapper.updateTicket(table,ticket);
    }

    @Override
    public int deleteTicketByOrderNumber(String date, String order_number) {
        String table = "ticket_"+date.replaceAll("-","");
        return ticketMapper.deleteTicketByOrderNumber(table, order_number);
    }
}
