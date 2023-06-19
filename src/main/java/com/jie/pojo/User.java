package com.jie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author jie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String account;
    private String password;
    private String username;
    private String phone_number;
    private String id_type;
    private String id_number;
    private String district;
    private String email;
    private Timestamp registration_time;
}
