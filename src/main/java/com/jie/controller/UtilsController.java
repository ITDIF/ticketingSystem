package com.jie.controller;

import com.jie.util.QiNiuYunUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/8/4 10:08
 */
@RestController
@RequestMapping("/utils")
public class UtilsController {

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        System.out.println("图片上传");
        return QiNiuYunUtil.upload(file);
    }
}
