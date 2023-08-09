package com.jie.controller;

import com.alibaba.fastjson2.JSON;
import com.jie.pojo.Admin;
import com.jie.pojo.User;
import com.jie.service.AdminService;
import com.jie.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author jie
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    UserService userService;
    @Resource
    AdminService adminService;

    @RequestMapping("/loginCheck")
    public Map<String, Object> loginCheck(String account, String password) {
        return userService.loginCheck(account, password);
    }
    @RequestMapping("phoneCheck")
    public boolean phoneCheck(String account, String phone) {
        return userService.checkPhoneByAccount(account, phone);
    }
    @RequestMapping("checkPhoneTrue")
    public boolean checkPhoneTrue(String phone) {
        return userService.checkPhoneTrue(phone);
    }

    @RequestMapping("/adminCheck")
    public int adminCheck(String account, String password) {
        return adminService.loginCheck(account, password);
    }

    @RequestMapping("/login")
    public User login(String account) {
        return userService.login(account);
    }

    @RequestMapping("/adminLogin")
    public Admin adminLogin(String account) {
        return adminService.login(account);
    }

    @RequestMapping("/userinfo")
    public Map<String, String> queryUserInfoByAccount(String account) {
        return userService.queryUserInfoByAccount(account);
    }

    @RequestMapping("/username")
    public String queryUsername(String account) {
        return userService.queryUsernameByAccount(account);
    }

    @RequestMapping("/gender")
    public int gender(String account) {
        return userService.queryIdNumberByAccount(account);
    }

    @RequestMapping("/update")
    public int updateUser(String data) {
        User user = JSON.parseObject(data, User.class);
        try {
            return userService.updateUser(user);
        } catch (Exception e) {
            return 0;
        }
    }

    @RequestMapping("/updateAdmin")
    public int updateAdmin(String data) {
        Admin admin = JSON.parseObject(data, Admin.class);
        try {
            return adminService.updateAdmin(admin);
        } catch (Exception e) {
            return 0;
        }
    }
}
