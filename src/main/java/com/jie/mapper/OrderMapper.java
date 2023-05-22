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
     * 添加订单
     * @param order
     * @return 结果
     */
    int addOrder(Order order);

    /**
     * 添加临时表
     * @param orderTemporary
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
     * @param id
     * @return 结果
     */
    int deleteOrderTemporaryById(int id);

}
