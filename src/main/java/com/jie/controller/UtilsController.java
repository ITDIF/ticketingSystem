package com.jie.controller;

import com.jie.service.Impl.RedisService;
import com.jie.util.QiNiuYunUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/8/4 10:08
 */
@RestController
@RequestMapping("/utils")
public class UtilsController {
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    RedisService redisService;

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        System.out.println("图片上传");
        return QiNiuYunUtil.upload(file);
    }
    @RequestMapping("/userMessage")
    public List<Map<String,String>> userMessage(String listName){
        return redisService.userMessage(listName);
    }
    @RequestMapping("/userOnline")
    public Set<String> userMessage(){
        return redisService.getUserOnline();
    }
    @RequestMapping("/userOnlineAndMessage")
    public List<Map<String,Object>> userOnlineAndMessage(){
        return redisService.onlineUserAndMessage();
    }
}
