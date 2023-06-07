package com.jie.controller;

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
@RequestMapping("/login")
public class LoginController {
    @Resource
    UserService userService;

    @RequestMapping("/loginCheck")
    @ResponseBody
    public String loginCheck(String account, String password) {
        System.out.println("loginCon:"+account+" "+password);
        return userService.loginCheck(account,password);
    }
    @RequestMapping("/login")
    @ResponseBody
    public User login(String account) {
        System.out.println("login:"+account);
        return userService.login(account);
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
}
