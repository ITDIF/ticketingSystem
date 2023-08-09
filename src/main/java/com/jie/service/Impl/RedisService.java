package com.jie.service.Impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
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
    public List<Map<String,String>> userMessage(String listName){
        if(!redisTemplate.hasKey(listName)){
            return new ArrayList<>();
        }
        return redisTemplate.opsForList().range(listName,0,-1);
    }
    public Set<String> getUserOnline(){
        if(!redisTemplate.hasKey("userHelpOnline")){
            return new HashSet<>();
        }
        return redisTemplate.opsForSet().members("userHelpOnline");
    }
    public Object getUserLastMessage(String account){
        String listName = "message-" + account;
        return redisTemplate.opsForList().index(listName,-1);
    }
    public List<Map<String,Object>> onlineUserAndMessage(){
        Set<String> userOnline = getUserOnline();
        List<Map<String,Object>> ans = new ArrayList<>();
        for(String e : userOnline){
            Map<String,Object> map = new HashMap<>();
            map.put("account",e);
            map.put("message",getUserLastMessage(e));
            ans.add(map);
        }
        return ans;
    }
}
