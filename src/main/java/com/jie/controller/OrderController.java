package com.jie.controller;

import com.jie.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author jie
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderService orderService;

    @RequestMapping("/temporary")
    @ResponseBody
    public String temporary(String a){



        return "1";
    }

}
