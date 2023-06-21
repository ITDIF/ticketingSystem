package com.jie.controller;

import com.alibaba.fastjson2.JSON;
import com.jie.pojo.User;
import com.jie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author jie
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    UserService userService;

    @RequestMapping("/loginCheck")
    @ResponseBody
    public Map<String,Object> loginCheck(String account, String password) {
        System.out.println("loginCon:"+account+" "+password);
        return userService.loginCheck(account,password);
    }
    @RequestMapping("/login")
    @ResponseBody
    public User login(String account) {
        return userService.login(account);
    }
    @RequestMapping("/userinfo")
    @ResponseBody
    public Map<String, String> queryUserInfoByAccount(String account){
        return userService.queryUserInfoByAccount(account);
    }
    @RequestMapping("/username")
    @ResponseBody
    public String queryUsername(String account) {
        return userService.queryUsernameByAccount(account);
    }
    @RequestMapping("/gender")
    @ResponseBody
    public int gender(String account) {
        return userService.queryIdNumberByAccount(account);
    }
    @RequestMapping("/update")
    @ResponseBody
    public int updateUser(String data){
        User user = JSON.parseObject(data,User.class);
        try {
            return userService.updateUser(user);
        }catch (Exception e){
            return 0;
        }
    }
}
