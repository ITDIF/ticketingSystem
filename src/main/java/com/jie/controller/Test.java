package com.jie.Test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test {
    @RequestMapping("/index")
    public String test1(){
        //classpath:/templates/test.html
        return "index";
    }
}
