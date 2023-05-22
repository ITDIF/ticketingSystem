package com.jie.service.Impl;

import com.jie.mapper.OrderMapper;
import com.jie.mapper.TicketMapper;
import com.jie.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jie
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    TicketMapper ticketMapper;
    @Resource
    OrderMapper orderMapper;

}
