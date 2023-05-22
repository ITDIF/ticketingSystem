package com.jie.controller;

import com.jie.service.Impl.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jie
 */
@RestController
public class HelloController {

    @Autowired
    MailService mailService;

    @GetMapping("/hello")
    public void hello() {
        mailService.sendSimpleMail("2084220263@QQ.com",
                "2387367830@qq.com",

                "2084220263@qq.com",
                "我是邮件的标题",
                "sb周显乐");
    }
}
