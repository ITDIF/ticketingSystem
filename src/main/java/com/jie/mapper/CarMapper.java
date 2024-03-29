package com.jie.mapper;

import com.jie.pojo.Car;
import com.jie.pojo.CarRentalFees;
import com.jie.pojo.CharteredBus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    List<CarRentalFees> queryCarRentalFees();

    List<Map<String, String>> queryCharteredBusInfo(String id_number);

    /**
     * 未使用车辆
     * @return 车辆编号集合
     */
    List<String> queryNotUseCar();
    List<Car> queryNotUseCarByType(String carType);

    /**
     * 添加car
     * @param car car
     * @return int
     */
    int addCar(Car car);
    int addCharteredBus(CharteredBus charteredBus);

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
    /**
     * 删除car
     * @param carNumber 账号
     * @return 结果
     */
    int delCarByCarNumber(String carNumber);

    int delCharteredBusByCarNumber(String carNumber);

    /**
     * 批量删除car
     * @param carNumbers car列表
     * @return 结果
     */
    int batchDelCarByCarNumber(List<String> carNumbers);
}
