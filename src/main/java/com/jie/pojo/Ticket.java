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
public class Ticket {
    private Integer id;
    private String order_number;
    private String route_number;
    private String username;
    private String id_number;
    private String from_station;
    private String to_station;
    private String seat_type;
    private Integer seat_id;
    private BigDecimal price;
    private Timestamp ticket_issuance_time;
}
