package com.jie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/8/16 14:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMoneyIntegral {
    private Integer id;
    private String account;
    private BigDecimal money;
    private int Integral;
    private String state;
}
