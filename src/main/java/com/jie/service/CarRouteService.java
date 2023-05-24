package com.jie.service;

import com.jie.pojo.CarRoute;

import java.util.List;

public interface CarRouteService {

    List<CarRoute> queryCarRouteList();

    CarRoute queryCarRouteById(Integer id);

    List<CarRoute> queryCarRouteBySED(String start, String end, String date);

    int addCarRoute(CarRoute carRoute);

    int updateCarRoute(CarRoute carRoute);

    int deleteCarRouteById(Integer id);
}
