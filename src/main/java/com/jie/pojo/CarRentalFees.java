package com.jie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/8/11 15:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRentalFees {
    private Integer id;
    private String car_type;
    private String busload;
    private BigDecimal price;
    private String status;
}
