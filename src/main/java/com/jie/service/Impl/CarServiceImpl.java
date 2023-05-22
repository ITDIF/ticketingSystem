package com.jie.service;

import com.jie.mapper.CarMapper;
import com.jie.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    CarMapper carMapper;

    public List<Car> queryCarList(){
        return carMapper.queryCarList();
    }

    public Car queryCarById(Integer id){
        return carMapper.queryCarById(id);
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
}
