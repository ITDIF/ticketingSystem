package com.jie.mapper;

import com.jie.pojo.Order;
import com.jie.pojo.OrderTemporary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jie
 */
@Mapper
@Repository
public interface OrderMapper {
    /**
     * 分页查询订单
     * @param start 开始
     * @param count 每次查询数量
     * @return 订单集合
     */
    List<Order> queryOrderListPaging(String start, String count);

    /**
     * 订单数量
     * @return 数量
     */
    int queryOrderCount();
    /**
     * 根据订单号查询下单时间
     * @param order_number 订单号
     * @return 下单时间
     */
    String queryOrderTimeByOrderNumber(String order_number);
    /**
     * 根据订单号查询下单时间和座位
     * @param order_number 订单号
     * @return 下单时间
     */
    Map<String, Object> queryOrderTimeAndSeatByOrderNumber(String order_number);

    /**
     * 根据账户查询所有的未完成订单
     * @param account 账户
     * @return 未完成订单
     */
    List<OrderTemporary> queryOrderTemporaryByAccount(String account);

    /**
     * 根据账户查询历史订单（候补除外）
     * @param account 账户
     * @return 历史订单
     */
    List<Order> queryHistoricalOrderByAccount(String account);
    /**
     * 根据多个条件查询历史订单（候补除外）
     * @param account 账户
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param key 关键字（用户名，订单，车次）
     * @param start 起点
     * @param count count条订单
     * @return 历史订单
     */
    List<Order> queryHistoricalOrderConditionalPaging(String account, String startDate, String endDate, String key, String start, String count);
    /**
     * 根据多个条件查询历史订单数量（候补除外）
     * @param account 账户
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param key 关键字（用户名，订单，车次）
     * @return 历史订单数量
     */
    int queryHistoricalOrderConditionalCount(String account, String startDate, String endDate, String key);
    /**
     * 根据身份证查询未出行订单
     * @param account 身份证
     * @param departure_time 出发时间
     * @param start 起点
     * @param count count条订单
     * @return 未出行订单
     */
    List<Order> queryNotTravelOrderByPaging(String account, String departure_time, String start, String count);

    /**
     * 根据账户查询历史订单分页
     * @param account 账户
     * @param start 起点
     * @param count count条订单
     * @return 订单
     */
    List<Order> queryHistoricalOrderPaging(String account, String start, String count);

    /**
     * 查询未出发订单数量
     * @param account 账户
     * @param departure_time 出发时间
     * @return 数量
     */
    int queryNotTravelOrderCount(String account, String departure_time);
    /**
     * 查询历史订单数量（候补订单除外）
     * @param account 账户
     * @return 数量
     */
    int queryHistoricalOrderCount(String account);
    /**
     * 根据临时订单编号查询
     * @param order_number 订单编号
     * @return 临时订单信息
     */
    OrderTemporary queryOrderTemporary(String order_number);

    /**
     * 根据订单编号查询
     * @param order_number 订单编号
     * @return 订单信息
     */
    Order queryOrder(String order_number);

    /**
     * 查询该订单号的车票是否存在
     * @param order_number 订单号
     * @return int
     */
    int queryOrderByOrderNumber(String order_number);

    /**
     * 根据订单号查询车次编号
     * @param order_number 订单号
     * @return 车次编号
     */
    String queryRouteNumberByOrderNumber(String order_number);
    /**
     * 添加订单
     * @param order 订单
     * @return 结果
     */
    int addOrder(Order order);

    /**
     * 添加临时订单
     * @param orderTemporary 临时订单
     * @return 结果
     */
    int addOrderTemporary(OrderTemporary orderTemporary);

    /**
     * 删除订单
     * @param order_number 订单编号
     * @return 结果
     */
    int deleteOrderByOrderNumber(String order_number);

    /**
     * 删除临时订单
     * @param id id
     * @return 结果
     */
    int deleteOrderTemporaryById(int id);

    /**
     * 根据订单编号删除订单
     * @param order_number 订单编号
     * @return int
     */
    int deleteOrderTemporaryByOrderNumber(String order_number);

    /**
     * 修改订单信息
     * @param order 订单
     * @return int
     */
    int updateOrder(Order order);


}
