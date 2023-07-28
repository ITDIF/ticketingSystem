package com.jie.service;

import com.jie.pojo.CarRoute;

import java.util.List;

public interface CarRouteService {

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

    List<CarRoute> queryCarRouteBySED(String start, String end, String date);

    int addCarRoute(CarRoute carRoute);

    int updateCarRoute(CarRoute carRoute);

    int deleteCarRouteById(Integer id);
    /**
     * 删除
     * @param routeNumber 编号
     * @return 结果
     */
    int delByRouteNumber(String routeNumber);

    /**
     * 批量删除
     * @param routeNumbers 编号集合
     * @return 结果
     */
    int batchDelByRouteNumber(List<String> routeNumbers);
}
