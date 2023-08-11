package com.jie.service.Impl;

import com.jie.mapper.CarMapper;
import com.jie.pojo.Car;
import com.jie.pojo.CarRentalFees;
import com.jie.service.CarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jie
 */
@Service
public class CarServiceImpl implements CarService {
    @Resource
    CarMapper carMapper;

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

    public int addCar(Car car){
        return carMapper.addCar(car);
    }

    public int updateCar(Car car){
        return carMapper.updateCar(car);
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
