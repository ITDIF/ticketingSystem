package com.jie.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.jie.mapper.CarMapper;
import com.jie.mapper.UserMapper;
import com.jie.pojo.Car;
import com.jie.pojo.CarRentalFees;
import com.jie.pojo.CharteredBus;
import com.jie.service.CarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author jie
 */
@Service
public class CarServiceImpl implements CarService {
    @Resource
    CarMapper carMapper;
    @Resource
    UserMapper userMapper;

    public List<Car> queryCarList(){
        return carMapper.queryCarList();
    }

    @Override
    public List<Car> queryCarPaging(String start, String count, String key, String value) {
        return carMapper.queryCarPaging(start, count, key, value);
    }

    @Override
    public List<CarRentalFees> queryCarRentalFees() {
        return carMapper.queryCarRentalFees();
    }

    @Override
    public int queryCarCount(String key, String value) {
        return carMapper.queryCarCount(key, value);
    }

    public Car queryCarById(Integer id){
        return carMapper.queryCarById(id);
    }

    @Override
    public List<String> queryNotUseCar() {
        return carMapper.queryNotUseCar();
    }

    @Override
    public List<Car> queryNotUseCarByType(String carType) {
        return carMapper.queryNotUseCarByType(carType);
    }

    @Override
    public List<Map<String, String>> queryCharteredBusInfo(String account) {
        return carMapper.queryCharteredBusInfo(userMapper.queryIdNumberByAccount(account));
    }

    public int addCar(Car car){
        return carMapper.addCar(car);
    }

    @Override
    public int addCharteredBus(String data, String account) {
        CharteredBus charteredBus = JSON.parseObject(data, CharteredBus.class);
        charteredBus.setId_number(userMapper.queryIdNumberByAccount(account));
        return carMapper.addCharteredBus(charteredBus);
    }

    public int updateCar(Car car){
        return carMapper.updateCar(car);
    }

    @Override
    public int delCharteredBusByCarNumber(String carNumber) {
        return carMapper.delCharteredBusByCarNumber(carNumber);
    }

    public int deleteCarById(Integer id){
        return carMapper.deleteCarById(id);
    }

    @Override
    public int delCarByCarNumber(String carNumber) {
        return carMapper.delCarByCarNumber(carNumber);
    }

    @Override
    public int batchDelCarByCarNumber(List<String> carNumbers) {
        return carMapper.batchDelCarByCarNumber(carNumbers);
    }
}
