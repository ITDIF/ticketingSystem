package com.jie.mapper;

import com.jie.pojo.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jie
 */
@Mapper
@Repository
public interface TicketMapper {
    /**
     * 分页查询
     * @param tableNames 表名
     * @param start 开始
     * @param count 每次查询数量
     * @param key 字段
     * @param value 字段值
     * @return 集合
     */
    List<Ticket> queryTicketListPaging(List<String> tableNames, String start, String count, String key, String value);

    /**
     * 数量
     * @param tableNames 表名
     * @param key 字段
     * @param value 字段值
     * @return 数量
     */
    int queryTicketCount(List<String> tableNames, String key, String value);
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
     *根据订单号查询待查日期的线路余票情况和线路编号（多表）
     * @param order_number 订单号
     * @param route_date 查询日期
     * @return map
     */
    Map<String, Object> queryRemainingTicketAndRouteNumber(String order_number, String route_date);

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
     * 查询用户车票数量
     * @param idNumber 身份证
     * @return 数量
     */
    int queryTicketCountByIdNumber(List<String> tableNames, String idNumber, String start, String end);

    /**
     * 查询用户车票信息
     * @param idNumber 身份证
     * @return 车票集合
     */
    List<Ticket> queryTicketByIdNumber(List<String> tableNames, String idNumber, String start, String end);

    /**
     * 查询数据库表名
     * @param table 表名（当前表名之后的表）
     * @return 表名集合
     */
    List<String> queryTableName(String table);
    /**
     * 查询所有数据库表名
     * @return 表名集合
     */
    List<String> queryAllTableName();

    /**
     * 查询所有车票
     * @param tableNames 表名
     * @return 结果
     */
    List<Ticket> queryAllTicket(List<String> tableNames);

    /**
     * 修改
     * @param ticket 车票
     * @param table 车票表
     * @return 结果
     */
    int updateTicket(@Param("table") String table, @Param("ticket")Ticket ticket);
    /**
     * 根据订单编号删除车票
     * @param table 待删的表
     * @param order_number 订单编号
     * @return int
     */
    int deleteTicketByOrderNumber(String table, String order_number);
}
