package com.jie.mapper;

import com.jie.pojo.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jie
 */
@Mapper
@Repository
public interface TicketMapper {
    /**
     * 查询车票表（ticket）是否存在
     * @param table 需要查询的表
     * @return string
     */
    String tableIsTrue(String table);

    /**
     * 创建车票信息表（无则创建）
     * @param table 需要创建的表
     * @return int
     */
    int createTableIsNotExist(String table);

    /**
     * 查询余票信息
     * @param route_number 线路编号
     * @param route_date 线路日期
     * @return 余票数
     */
    String queryRemainingTicket(String route_number, String route_date);

    /**
     * 在route_remaining_ticket表中添加一行信息
     * @param route_number 线路编号
     * @param remaining_tickets 余票
     * @param route_date 日期
     * @return int
     */
    int insertRemainingTicketInfo(String route_number, String remaining_tickets, String route_date);

    /**
     * 根据线路编号查询客车容量
     * @param route_number 线路编号
     * @return 客车容量
     */
    int queryPassengerCapacityByRouteNumber(String route_number);

    /**
     * 添加车票到table表中
     * @param table
     * @param ticket
     * @return int
     */
    int addTicket(@Param("table") String table, @Param("ticket") Ticket ticket);

    /**
     * 更新余票表
     * @param remaining_tickets 余票
     * @param route_number 线路编号
     * @param route_date 日期
     * @return int
     */
    int updateRemainingTicket(String remaining_tickets, String route_number, String route_date);

    /**
     * 查询已购车票
     * @param ticket 待查的车票表
     * @param route_number 线路编号
     * @return 座位集合
     */
    List<Integer> querySeat(String ticket,String route_number);

    /**
     * 根据订单编号删除车票
     * @param table 待删的表
     * @param order_number 订单编号
     * @return int
     */
    int deleteTicketByOrderNumber(String table, String order_number);
}
