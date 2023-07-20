package com.jie.controller;

import com.jie.pojo.Order;
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

    @RequestMapping("/queryOrderListPaging")
    public List<Order> queryOrderListPaging(String start, String count){
        return orderService.queryOrderListPaging(start,count);
    }
    @RequestMapping("/queryOrderCount")
    public int queryOrderCount(){
        return orderService.queryOrderCount();
    }
}
