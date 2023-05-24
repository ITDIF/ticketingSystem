package com.jie.mapper;

import com.jie.pojo.Order;
import com.jie.pojo.OrderTemporary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author jie
 */
@Mapper
@Repository
public interface OrderMapper {
    /**
     * 根据订单号查询下单时间
     * @param order_number 订单号
     * @return 下单时间
     */
    String queryOrderTimeByOrderNumber(String order_number);

    /**
     * 根据临时订单编号查询
     * @param order_number 订单编号
     * @return 临时订单信息
     */
    OrderTemporary queryOrderTemporary(String order_number);

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
     * @param id
     * @return 结果
     */
    int deleteOrderById(int id);

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


}
