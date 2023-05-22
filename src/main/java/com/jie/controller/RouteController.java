package com.jie.controller;

import com.jie.pojo.CarRoute;
import com.jie.service.CarRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author jie
 */
@Controller
@RequestMapping("/route")
public class RouteController {
    @Autowired
    CarRouteService carRouteService;

    @RequestMapping("/queryCarRouteBySE")
    @ResponseBody
    public List<CarRoute> queryCarRouteBySE(String start, String end, String date){
        System.out.println("queryCarRouteBySE "+start+" "+end+" "+date);
        return carRouteService.queryCarRouteBySE(start,end,date);
    }
}
