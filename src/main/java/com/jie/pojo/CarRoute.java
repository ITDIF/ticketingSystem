package com.jie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author jie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRoute {
    private Integer id;
    private String route_number;
    private String car_number;
    private String from_station;
    private String to_station;
    private String departure_time;
    private Integer mileage;
    private BigDecimal price;
    private Integer remaining_tickets;
    private String seat_type;
}
