package com.jie.util;

import java.math.BigDecimal;

/**
 * Description:MD5测试类
 */
public class Md5Test{

    public static void main(String[] args) {

//        String keyword="i love you";
//        String md5= DigestUtils.md5Hex(keyword);
//        System.out.println("md5加密后："+"\n"+md5);
//        String md5salt= MD5.md5PlusSalt(keyword);
//        System.out.println("加盐后："+"\n"+md5salt);
//        String word= MD5.md5MinusSalt(md5salt);
//        System.out.println("解密后："+"\n"+word);
        BigDecimal a = new BigDecimal(10);
        BigDecimal b = new BigDecimal(20);
        BigDecimal c = new BigDecimal(-20);
        System.out.println(a.add(b));
        System.out.println(a.add(c));
    }
}
