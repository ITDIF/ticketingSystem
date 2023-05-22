package com.jie.service.Impl;

import com.jie.mapper.TicketMapper;
import com.jie.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jie
 */
@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketMapper ticketMapper;

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
}
