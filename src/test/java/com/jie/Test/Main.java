package com.jie.Test;

public class Main {
    public static void main(String[] args) {
//        Date date = new Date();
//        Timestamp timeStamp = new Timestamp(date.getTime());
//        System.out.println(new Timestamp(new Date().getTime()));
//        System.out.println(timeStamp);
//        String s = Integer.toBinaryString(1);
        long mod = 1000000007L;
        long ans = 1000000000;
        System.out.println(ans * ans % mod * ans);
    }
}
