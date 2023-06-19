package com.jie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 候补
 * @author jie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    private Integer id;
    private String route_number;
    private String order_number;
    private String id_number;
    private Timestamp departure_time;
    private Timestamp candidate_time;
    private Integer deadline;
    private String state;
}
