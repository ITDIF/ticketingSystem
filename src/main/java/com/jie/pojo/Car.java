package com.jie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * car 车
 * @author jie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private Integer id;
    private String car_number;
    private String car_type;
    private String seat_type;
    private Integer passenger_capacity;
}
