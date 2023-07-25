package com.jie.service.Impl;

import com.jie.mapper.OrderTemporaryMapper;
import com.jie.pojo.OrderTemporary;
import com.jie.service.OrderTemporaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/7/24 9:36
 */
@Service
public class OrderTemporaryServiceImpl implements OrderTemporaryService {
    @Resource
    OrderTemporaryMapper orderTemporaryMapper;

    @Override
    public List<OrderTemporary> queryOrderTemporaryPaging(String start, String count, String key, String value) {
        return orderTemporaryMapper.queryOrderTemporaryPaging(start, count, key, value);
    }

    @Override
    public int queryOrderTemporaryCount(String key, String value) {
        return orderTemporaryMapper.queryOrderTemporaryCount(key, value);
    }
}
