package com.jie.Test;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
//        Date date = new Date();
//        Timestamp timeStamp = new Timestamp(date.getTime());
//        System.out.println(new Timestamp(new Date().getTime()));
//        System.out.println(timeStamp);
//        String s = Integer.toBinaryString(1);
        System.out.println(0|0);
        System.out.println(0|1);
        System.out.println(1|1);
        System.out.println(1|0);
        System.out.println("1".compareTo("3"));
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->o2-o1);
        pq.add(3);
        pq.add(1);
        pq.add(2);
        System.out.println(pq);
    }
}
