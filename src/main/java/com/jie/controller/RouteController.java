package com.jie.controller;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jie.pojo.CarRoute;
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
    @RequestMapping("/add")
    public int addCarRoute(String data){
        return carRouteService.addCarRoute(JSON.parseObject(data, CarRoute.class));
    }
    @RequestMapping("/update")
    public int updateCarRoute(String data){
        return carRouteService.updateCarRoute(JSON.parseObject(data, CarRoute.class));
    }
    @RequestMapping("/del")
    public int delCarRoute(String routeNumber){
        return carRouteService.delByRouteNumber(routeNumber);
    }
    @RequestMapping("/batchDel")
    public int batchDelCarRoute(String routeNumbers) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> list = mapper.readValue(routeNumbers, new TypeReference<>() {});
        return carRouteService.batchDelByRouteNumber(list);
    }
}
