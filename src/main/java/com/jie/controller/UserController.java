package com.jie.controller;

import com.alibaba.fastjson2.JSON;
import com.jie.pojo.User;
import com.jie.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jie
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @RequestMapping("/checkPass")
    public boolean checkPass(String account, String password) {
        System.out.println("checkPass:"+account);
        return userService.checkPass(account,password);
    }
    @RequestMapping("/phoneCode")
    public int sendPhoneCode(String account) {
        return userService.sendPhoneCode(account);
    }
    @RequestMapping("/newPhoneCode")
    public int sendNewPhoneCode(String phone) {
        return userService.sendCode(phone);
    }
    @RequestMapping("/mailCode")
    public int sendMailCode(String email) {
        return userService.sendMailCode(email);
    }
    @RequestMapping("/checkCodeAndUpdateUser")
    public int checkCodeAndUpdateUser(int code, String data) {
        System.out.println(code+" "+data);
        User user = JSON.parseObject(data,User.class);
        System.out.println(user);
        return userService.checkCodeAndUpdateUser(code,user);
    }
    @RequestMapping("/checkMailCodeAndUpdateUser")
    public int checkMailCodeAndUpdateUser(int code, String data) {
        System.out.println(code+" "+data);
        User user = JSON.parseObject(data,User.class);
        System.out.println(user);
        return userService.checkMailCodeAndUpdateUser(code,user);
    }
    @RequestMapping("/userMail")
    public String userEmail(String account) {
        return userService.userEmail(account);
    }

}
