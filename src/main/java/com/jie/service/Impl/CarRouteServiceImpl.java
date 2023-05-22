package com.jie.service;

import com.jie.mapper.CarRouteMapper;
import com.jie.pojo.CarRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarRouteServiceImpl implements CarRouteService{
    @Autowired
    CarRouteMapper carRouteMapper;

    @Override
    public List<CarRoute> queryCarRouteList() {
        return carRouteMapper.queryCarRouteList();
    }

    @Override
    public CarRoute queryCarRouteById(Integer id) {
        return carRouteMapper.queryCarRouteById(id);
    }

    @Override
    public List<CarRoute> queryCarRouteByRoute(String start, String end) {
        return carRouteMapper.queryCarRouteByRoute(start,end);
    }

    @Override
    public int addCarRoute(CarRoute carRoute) {
        return carRouteMapper.addCarRoute(carRoute);
    }

    @Override
    public int updateCarRoute(CarRoute carRoute) {
        return carRouteMapper.updateCarRoute(carRoute);
    }

    @Override
    public int deleteCarRouteById(Integer id) {
        return carRouteMapper.deleteCarRouteById(id);
    }

}
