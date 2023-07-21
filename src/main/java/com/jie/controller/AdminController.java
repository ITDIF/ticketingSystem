package com.jie.controller;

import com.jie.pojo.Admin;
import com.jie.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/7/20 20:54
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminService adminService;

    @RequestMapping("/queryAdminPaging")
    public List<Admin> queryUserPaging(String start, String count, String key, String value){
        return adminService.queryAdminPaging(start,count,key,value);
    }
    @RequestMapping("/queryAdminCount")
    public int queryUserCount(String key, String value){
        return adminService.queryAdminCount(key, value);
    }
}
