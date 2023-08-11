package com.jie.controller;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jie.pojo.Car;
import com.jie.pojo.Order;
import com.jie.pojo.OrderTemporary;
import com.jie.service.CarService;
import com.jie.service.OrderService;
import com.jie.service.OrderTemporaryService;
import com.jie.service.UserService;
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
    @Resource
    OrderTemporaryService orderTemporaryService;
    @Resource
    UserService userService;

    @RequestMapping("/queryOrderListPaging")
    public List<Order> queryOrderListPaging(String start, String count,String key, String value){
        return orderService.queryOrderListPaging(start,count,key,value);
    }
    @RequestMapping("/queryOrderCount")
    public int queryOrderCount(String key, String value){
        return orderService.queryOrderCount(key, value);
    }
    @RequestMapping("/queryOrderTemporaryPaging")
    public List<OrderTemporary> queryOrderTemporaryPaging(String start, String count, String key, String value){
        return orderTemporaryService.queryOrderTemporaryPaging(start,count,key,value);
    }
    @RequestMapping("/queryOrderTemporaryCount")
    public int queryOrderTemporaryCount(String key, String value){
        return orderTemporaryService.queryOrderTemporaryCount(key, value);
    }
    @RequestMapping("/queryCarPaging")
    public List<Car> queryCarPaging(String start, String count, String key, String value){
        return carService.queryCarPaging(start,count,key,value);
    }
    @RequestMapping("/queryCarCount")
    public int queryCarCount(String key, String value){
        return carService.queryCarCount(key, value);
    }
    @RequestMapping("/batchDelUser")
    public int batchDelUser(String accounts) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> list = mapper.readValue(accounts, new TypeReference<>() {});
        return userService.batchDelUserByAccount(list);
    }
    @RequestMapping("/addCar")
    public int addCar(String data){
        return carService.addCar(JSON.parseObject(data, Car.class));
    }
    @RequestMapping("/updateCar")
    public int updateCar(String data){
        return carService.updateCar(JSON.parseObject(data, Car.class));
    }
    @RequestMapping("/delCar")
    public int delCar(String carNumber){
        return carService.delCarByCarNumber(carNumber);
    }
    @RequestMapping("/batchDelCar")
    public int batchDelCar(String carNumbers) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> list = mapper.readValue(carNumbers, new TypeReference<>() {});
        return carService.batchDelCarByCarNumber(list);
    }
    @RequestMapping("/upOrderAndTicket")
    public int upOrderAndTicket(String data, String date){
        return orderService.upOrderAndTicket(JSON.parseObject(data, Order.class),date);
    }
    @RequestMapping("/notUseCar")
    public List<String> queryNotUseCar() {
        return carService.queryNotUseCar();
    }
}
