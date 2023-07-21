package com.jie.mapper;

import com.jie.pojo.Car;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jie
 */
@Mapper
@Repository
public interface CarMapper {
    /**
     * 查询所有car
     * @return car集合
     */
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

    /**
     * 通过id查car
     * @param id id
     * @return Car
     */
    Car queryCarById(Integer id);

    /**
     * 添加car
     * @param car car
     * @return int
     */
    int addCar(Car car);

    /**
     * 修改car
     * @param car car
     * @return int
     */
    int updateCar(Car car);

    /**
     *删除car
     * @param id id
     * @return int
     */
    int deleteCarById(Integer id);

    /**
     * 根据客车编号查询座位类型
     * @param car_number 客车编号
     * @return 座位类型
     */
    String querySeatByCarNumber(String car_number);
}
