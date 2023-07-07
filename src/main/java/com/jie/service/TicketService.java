package com.jie.service;

import com.jie.pojo.Ticket;

import java.util.List;

/**
 * @author jie
 */
public interface TicketService {
    String tableIsTrue(String table);

    int createTableIsNotExist(String table);

    /**
     *根据线路编号查询待查日期的线路余票情况
     * @param route_number 线路编号
     * @param route_date 查询日期
     * @return 与票数
     */
    int queryRemainingTicket(String route_number, String route_date);

    List<Ticket> queryTicketByIdNumber(String date, String account, String start, String end);


}
