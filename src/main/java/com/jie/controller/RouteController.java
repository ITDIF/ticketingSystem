package com.jie.controller;

import com.jie.pojo.CarRoute;
import com.jie.pojo.Order;
import com.jie.service.CarRouteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jie
 */
@RestController
@RequestMapping("/route")
public class RouteController {
    @Resource
    CarRouteService carRouteService;

    @RequestMapping("/queryCarRouteBySED")
    public List<CarRoute> queryCarRouteBySED(String start, String end, String date){
        System.out.println("queryCarRouteBySED "+start+" "+end+" "+date);
        return carRouteService.queryCarRouteBySED(start,end,date);
    }
    @RequestMapping("/queryCarRoutePaging")
    public List<CarRoute> queryOrderListPaging(String start, String count, String key, String value){
        return carRouteService.queryCarRoutePaging(start,count,key,value);
    }
    @RequestMapping("/queryCarRouteCount")
    public int queryOrderCount(String key, String value){
        return carRouteService.queryCarRouteCount(key, value);
    }
}
