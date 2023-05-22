package com.jie.service;

import com.jie.pojo.Car;

import java.util.List;

public interface CarService {
    List<Car> queryCarList();

    Car queryCarById(Integer id);

    int addCar(Car car);

    int updateCar(Car car);

    int deleteCarById(Integer id);
}
