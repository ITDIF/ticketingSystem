package com.jie.controller;

import com.jie.pojo.CarRentalFees;
import com.jie.service.CarService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

}
