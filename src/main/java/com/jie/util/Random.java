package com.jie.util;

/**
 * @author jie
 */
public class Random {
    public static int getRandomPhoneCode4(){
        return (int)((Math.random()*9+1)*1000);
    }
}
