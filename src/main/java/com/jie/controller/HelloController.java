package com.jie.controller;

import com.jie.service.Impl.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jie
 */
@RestController
public class HelloController {

    @Resource
    MailService mailService;

    @GetMapping("/hello")
    public void hello() {
        mailService.sendSimpleMail("2084220263@QQ.com",
                "2387367830@qq.com",

                "2084220263@qq.com",
                "我是邮件的标题",
                "sb周显乐");
    }
    @GetMapping("ticket")
    public void ticket(){
        mailService.sendTextEmail("您买了一张车票","3059880893@qq.com","购买车票成功!");
    }
}
