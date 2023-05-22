package com.jie.controller;

import com.jie.pojo.User;
import com.jie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jie
 */
@Controller
@RequestMapping("/Test")

public class Test {
    @Resource
    UserService userService;

    @RequestMapping("/msg")
    @ResponseBody
    public String test1(Model model){
        model.addAttribute("msg","Hello,Thymeleaf");
        return "hhhhhhhhhhhh";
    }
    @RequestMapping("/queryList")
    @ResponseBody
    public List<User> queryUserList(){
        return userService.queryUserList();
    }
}
