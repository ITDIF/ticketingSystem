package com.jie.controller;

import com.jie.pojo.Car;
import com.jie.pojo.CarRentalFees;
import com.jie.service.CarService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/8/11 15:10
 */
@RestController
@RequestMapping("/car")
public class CarController {
    @Resource
    CarService carService;
    @RequestMapping("/queryCarRentalFees")
    public List<CarRentalFees> queryCarRentalFees() {
        return carService.queryCarRentalFees();
    }
    @RequestMapping("/notUseCarByType")
    public List<Car> queryNotUseCarByType(String carType) {
        System.out.println("queryNotUseCarByType "+carType);
        return carService.queryNotUseCarByType(carType);
    }
    @RequestMapping("/addCharteredBus")
    public int addCharteredBus(String data, String account) {
        return carService.addCharteredBus(data, account);
    }
    @RequestMapping("/queryCharteredBusInfo")
    public List<Map<String, String>> queryCharteredBusInfo(String account) {
        return carService.queryCharteredBusInfo(account);
    }
    @RequestMapping("/delCharteredBus")
    public int delCharteredBusByCarNumber(String carNumber) {
        return carService.delCharteredBusByCarNumber(carNumber);
    }

}
