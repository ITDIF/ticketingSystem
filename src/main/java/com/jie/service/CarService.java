package com.jie.service;

import com.jie.pojo.Car;

import java.util.List;

public interface CarService {
    List<Car> queryCarList();
    /**
     * 分页查询车辆
     * @param start 开始
     * @param count 每次查询数量
     * @param key 字段
     * @param value 字段值
     * @return 车辆集合
     */
    List<Car> queryCarPaging(String start, String count, String key, String value);

    /**
     * 车辆数量
     * @param key 字段
     * @param value 字段值
     * @return 数量
     */
    int queryCarCount(String key, String value);

    Car queryCarById(Integer id);

    int addCar(Car car);

    int updateCar(Car car);

    int deleteCarById(Integer id);
}
