package com.jie.controller;

import com.jie.pojo.Car;
import com.jie.pojo.Order;
import com.jie.service.CarService;
import com.jie.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/7/19 11:35
 */
@RestController
@RequestMapping("/manage")
public class ManageController {
    @Resource
    OrderService orderService;
    @Resource
    CarService carService;

    @RequestMapping("/queryOrderListPaging")
    public List<Order> queryOrderListPaging(String start, String count,String key, String value){
        System.out.println("queryOrderListPaging "+key+" "+value);
        return orderService.queryOrderListPaging(start,count,key,value);
    }
    @RequestMapping("/queryOrderCount")
    public int queryOrderCount(String key, String value){
        System.out.println("queryOrderCount "+key+" "+value);
        return orderService.queryOrderCount(key, value);
    }
    @RequestMapping("/queryCarPaging")
    public List<Car> queryCarPaging(String start, String count, String key, String value){
        return carService.queryCarPaging(start,count,key,value);
    }
    @RequestMapping("/queryCarCount")
    public int queryCarCount(String key, String value){
        return carService.queryCarCount(key, value);
    }
}
