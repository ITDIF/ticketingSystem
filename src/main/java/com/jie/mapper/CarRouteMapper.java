package com.jie.mapper;

import com.jie.pojo.CarRoute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jie
 */
@Mapper
@Repository
public interface CarRouteMapper {
    /**
     * 查询所有车次信息
     * @return 集合
     */
    List<CarRoute> queryCarRouteList();

    /**
     * 分页查询线路
     * @param start 开始
     * @param count 每次查询数量
     * @param key 字段
     * @param value 字段值
     * @return 线路集合
     */
    List<CarRoute> queryCarRoutePaging(String start, String count, String key, String value);

    /**
     * 线路数量
     * @param key 字段
     * @param value 字段值
     * @return 数量
     */
    int queryCarRouteCount(String key, String value);

    CarRoute queryCarRouteById(Integer id);

    /**
     * 通过车次编号查车次信息
     * @param route_number 车次
     * @return 对象
     */
    CarRoute queryCarRouteByRouteNumber(String route_number);

    List<CarRoute> queryCarRouteBySE(String start, String end);

    int addCarRoute(CarRoute carRoute);

    int updateCarRoute(CarRoute carRoute);

    int deleteCarRouteById(Integer id);
}
