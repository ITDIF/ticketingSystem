package com.jie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/8/11 11:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharteredBus {
    private Integer id;
    private String car_number;
    private String id_number;
    private Date start_time;
    private Date end_time;
    private String notes;
    private BigDecimal price;
    private String status;
}
