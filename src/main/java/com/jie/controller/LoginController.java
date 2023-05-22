package com.jie.controller;

import com.jie.pojo.User;
import com.jie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jie
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
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
}
