package com.jie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author jie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private String order_number;
    private String username;
    private String route_number;
    private String id_number;
    private Timestamp departure_time;
    private String from_station;
    private String to_station;
    private String seat_type;
    private Integer seat_id;
    private BigDecimal price;
    private Timestamp order_time;
    private String state;
    private Timestamp pay_time;
}
