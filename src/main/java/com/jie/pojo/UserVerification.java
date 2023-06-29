package com.jie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVerification {
    private Integer id;
    private String account;
    private String id_verification;
    private String phone_verification;
}
