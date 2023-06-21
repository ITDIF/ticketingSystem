package com.jie.controller;

import com.alibaba.fastjson2.JSON;
import com.jie.pojo.User;
import com.jie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author jie
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @RequestMapping("/checkPass")
    @ResponseBody
    public boolean checkPass(String account, String password) {
        System.out.println("checkPass:"+account);
        return userService.checkPass(account,password);
    }
    @RequestMapping("/phoneCode")
    @ResponseBody
    public int sendPhoneCode(String account) {
        return userService.sendPhoneCode(account);
    }
    @RequestMapping("/checkCodeAndUpdateUser")
    @ResponseBody
    public int checkCodeAndUpdateUser(int code, String data) {
        System.out.println(code+" "+data);
        User user = JSON.parseObject(data,User.class);
        System.out.println(user);
        return userService.checkCodeAndUpdateUser(code,user);
    }
}
