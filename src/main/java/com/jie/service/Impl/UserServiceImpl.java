package com.jie.service.Impl;

import com.jie.mapper.UserMapper;
import com.jie.pojo.User;
import com.jie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jie
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public String loginCheck(String account, String password) {
        User user;
        System.out.println("login: " + account+" "+password);
        //判断账号是手机号登录还是账号登录
        if(account.charAt(0)>='0' && account.charAt(0)<='9' && account.length()==11){
            user = userMapper.queryUserByNumber(Long.parseLong(account));
        }else{
            user = userMapper.queryUserByAccount(account);
        }
        if (user == null) {
            return "账户不存在!";
        }else if (user.getPassword().equals(password)) {
            System.out.println("登录成功！");
            return "登录成功!";
        }
        return "密码错误！";
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
            user = userMapper.queryUserByNumber(Long.parseLong(account));
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

    public User queryUserByNumber(long number){
        return userMapper.queryUserByNumber(number);
    }
    public User queryUserByAccount(String account){
        return userMapper.queryUserByAccount(account);
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

    public int deleteUserById(Integer id){
        return userMapper.deleteUserById(id);
    }
}
