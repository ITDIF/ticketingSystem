package com.jie.controller;

import com.alibaba.fastjson2.JSON;
import com.jie.pojo.User;
import com.jie.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * @author jie
 */
@RestController
@CrossOrigin
@RequestMapping("/register")
public class RegisterController {
    @Resource
    UserService userService;

    @RequestMapping("/queryAccount")
    public boolean queryAccount(String account) {
        System.out.println("queryAccount:"+account);
        return userService.queryAccount(account);
    }

    /**
     * 添加User（用户）
     * @param data 前端传来的注册信息
     * @return 注册状态（成功/失败）
     */
    @RequestMapping("/addUser")
    public int addUser(String data){
        User user = JSON.parseObject(data,User.class);
        user.setRegistration_time(new Timestamp(System.currentTimeMillis()));
        int result = 0;
        try {
            result =  userService.addUser(user);
        }catch (Exception e){
            System.out.println("注册失败！");
        }
        return result;
    }
}
