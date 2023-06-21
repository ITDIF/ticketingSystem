package com.jie.service.Impl;

import com.jie.mapper.UserMapper;
import com.jie.pojo.User;
import com.jie.service.SendMsgService;
import com.jie.service.UserService;
import com.jie.util.Random;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jie
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    SendMsgService sendMsgService;
    @Resource
    RedisTemplate redisTemplate;


    @Override
    public Map<String, Object> loginCheck(String account, String password) {
        User user;
        Map<String, Object> map = new HashMap<>();
        System.out.println("login: " + account+" "+password);
        //判断账号是手机号登录还是账号登录
        if(account.charAt(0)>='0' && account.charAt(0)<='9' && account.length()==11){
            user = userMapper.queryUserByNumber(account);
        }else{
            user = userMapper.queryUserByAccount(account);
        }
        if (user == null) {
            map.put("code",0);
            return map;
        }else if (user.getPassword().equals(password)) {
            System.out.println("登录成功！");
            map.put("code",1);
            map.put("account", user.getAccount());
            map.put("username", user.getUsername());
            return map;
        }
        map.put("code",-1);
        return map;
    }

    @Override
    public Map<String, String> queryUserInfoByAccount(String account) {
        return userMapper.queryUserInfoByAccount(account);
    }

    @Override
    public User login(String account) {
        User user;
        //判断账号是手机号登录还是账号登录
        if(account.charAt(0)>='0' && account.charAt(0)<='9' && account.length()==11){
            user = userMapper.queryUserByNumber(account);
        }else{
            user = userMapper.queryUserByAccount(account);
        }
        return user;
    }

    @Override
    public List<User> queryUserList(){
        return userMapper.queryUserList();
    }

    @Override
    public int queryIdNumberByAccount(String account) {
        String idNumber = userMapper.queryIdNumberByAccount(account);
        int gender = idNumber.charAt(idNumber.length()-2)-'0';
        if(gender % 2 == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public boolean queryAccount(String account) {
        int ans = userMapper.queryAccount(account);
        return ans == 1;
    }

    public User queryUserById(Integer id){
        return userMapper.queryUserById(id);
    }

    public User queryUserByNumber(String number){
        return userMapper.queryUserByNumber(number);
    }
    public User queryUserByAccount(String account){
        return userMapper.queryUserByAccount(account);
    }

    @Override
    public boolean checkPass(String account, String password) {
        return password.equals(userMapper.queryPassword(account));
    }

    @Override
    public int sendPhoneCode(String account) {
        String phone = userMapper.queryPhoneByAccount(account);
        int code = Random.getRandomPhoneCode4();
        Map<String,Integer> map = new HashMap<>();
        map.put("code",code);
        boolean isTrue = sendMsgService.sendMsg(phone,map);
        if(isTrue){
            redisTemplate.opsForValue().set(phone,map.get("code"),65, TimeUnit.SECONDS);
            return 1;
        }
        return 0;
    }

    @Override
    public String queryUsernameByAccount(String account) {
        return userMapper.queryUsernameByAccount(account);
    }

    public int addUser(User user){
        return userMapper.addUser(user);
    }

    public int updateUser(User user){
        return userMapper.updateUser(user);
    }

    @Override
    public int checkCodeAndUpdateUser(int code, User user) {
        String phone = userMapper.queryPhoneByAccount(user.getAccount());
        if(!redisTemplate.hasKey(phone)){
            return -2;
        }
        int redisCode = (int) redisTemplate.opsForValue().get(phone);
        if(code == redisCode){
            return userMapper.updateUser(user);
        }else{
            return -1;
        }
    }

    public int deleteUserById(Integer id){
        return userMapper.deleteUserById(id);
    }
}
