package com.jie.controller;

import com.jie.pojo.CarRoute;
import com.jie.service.CarRouteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jie
 */
@Controller
@RequestMapping("/route")
public class RouteController {
    @Resource
    CarRouteService carRouteService;

    @RequestMapping("/queryCarRouteBySED")
    @ResponseBody
    public List<CarRoute> queryCarRouteBySED(String start, String end, String date){
        System.out.println("queryCarRouteBySED "+start+" "+end+" "+date);
        return carRouteService.queryCarRouteBySED(start,end,date);
    }
}
