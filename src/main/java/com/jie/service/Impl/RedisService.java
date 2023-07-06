package com.jie.service.Impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author jie
 */
@Service
public class RedisService {
    @Resource
    RedisTemplate redisTemplate;

    public void setRebookOrderNumber(String orderNumber, String rebookOrderNumber){
        redisTemplate.opsForValue().set("toRebook"+orderNumber, rebookOrderNumber,600, TimeUnit.SECONDS);
    }
    public String getRebookOrderNumber(String orderNumber){
        return (String) redisTemplate.opsForValue().get("toRebook"+orderNumber);
    }
}
