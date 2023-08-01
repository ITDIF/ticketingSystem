package com.jie.service;

import com.jie.pojo.Ticket;

import java.util.List;

/**
 * @author jie
 */
public interface TicketService {
    /**
     * 分页查询
     * @param start 开始
     * @param count 每次查询数量
     * @param key 字段
     * @param value 字段值
     * @return 集合
     */
    List<Ticket> queryTicketListPaging(String start, String count, String key, String value);

    /**
     * 数量
     * @param key 字段
     * @param value 字段值
     * @return 数量
     */
    int queryTicketCount(String key, String value);
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


    /**
     * 查询所有车票
     * @return 结果
     */
    List<Ticket> queryAllTicket();
    /**
     * 添加车票到table表中
     * @param date
     * @param ticket
     * @return int
     */
    int addTicket(String date, Ticket ticket);

    /**
     * 修改
     * @param ticket 车票
     * @param date 时间
     * @return 结果
     */
    int updateTicket(Ticket ticket, String date);

    /**
     * 根据订单编号删除车票
     * @param date 时间
     * @param order_number 订单编号
     * @return int
     */
    int deleteTicketByOrderNumber(String date, String order_number);
}
