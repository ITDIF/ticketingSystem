package com.jie.controller;

import com.alibaba.fastjson2.JSON;
import com.jie.pojo.User;
import com.jie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * @author jie
 */
@Controller
@CrossOrigin
@RequestMapping("/register")
public class RegisterController {
    @Resource
    UserService userService;

    @RequestMapping("/queryAccount")
    @ResponseBody
    public boolean queryAccount(String account) {
        System.out.println("queryAccount:"+account);
        return userService.queryAccount(account);
    }
//    @RequestMapping("/add")
//    @ResponseBody
//    public int addUser(String account, String password, String name, String idType, String id, String email, String number){
//        User user = new User(null,account,password,name,Long.parseLong(number),idType,id,null,email,new Timestamp(new Date().getTime()));
//        System.out.println("注册账户:"+user);
//        int result = 0;
//        try {
//            result =  userService.addUser(user);
//        }catch (Exception e){
//            System.out.println("注册失败！");
//        }
//        return result;
//    }

    /**
     * 添加User（用户）
     * @param data 前端传来的注册信息
     * @return 注册状态（成功/失败）
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public int addUser(String data){
        User user = JSON.parseObject(data,User.class);
        user.setRegistration_time(new Timestamp(System.currentTimeMillis()));
        System.out.println("注册账户:"+user);
        int result = 0;
        try {
            result =  userService.addUser(user);
        }catch (Exception e){
            System.out.println("注册失败！");
        }
        return result;
    }
}
