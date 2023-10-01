package com.jie.controller;

import com.jie.mapper.UserMapper;
import com.jie.pojo.User;
import com.jie.pojo.UserMoneyIntegral;
import com.jie.service.UserService;
import com.jie.util.QiNiuYunUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author jie
 */
@RestController
@RequestMapping("/Test")

public class Test {
    @Resource
    UserService userService;
    @Resource
    UserMapper userMapper;

    @RequestMapping("/msg")
    public String test1(Model model){
        model.addAttribute("msg","Hello,Thymeleaf");
        return "hhhhhhhhhhhh";
    }
    @RequestMapping("/queryList")
    public List<User> queryUserList(){
        return userService.queryUserList();
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        System.out.println("------------------------------");
        System.out.println("test "+file);
        return QiNiuYunUtil.upload(file);
    }
    @RequestMapping("init")
    public void init(){
        List<User> list = userService.queryUserList();
        for(User user : list){
            UserMoneyIntegral userMoneyIntegral = new UserMoneyIntegral(null,user.getAccount(),new BigDecimal(10000),1000000,"");
            userMapper.addMoneyAndIntegral(userMoneyIntegral);
        }
    }
}
