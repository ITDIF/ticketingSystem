package com.jie.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Orderinfo {
    private Integer id;
    private String order_number;
    private String username;
    private String route_number;
    private String id_number;
    private Timestamp departure_time;
    private String from_station;
    
}
