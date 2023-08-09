package com.jie.service.Impl;

import com.jie.mapper.UserMapper;
import com.jie.pojo.User;
import com.jie.pojo.UserVerification;
import com.jie.service.SendMsgService;
import com.jie.service.UserService;
import com.jie.util.Random;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    MailService mailService;


    @Override
    public List<User> queryUserPaging(String start, String count, String key, String value) {
        return userMapper.queryUserPaging(start, count, key, value);
    }

    @Override
    public int queryUserCount(String key, String value) {
        return userMapper.queryUserCount(key, value);
    }

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
    public String userEmail(String account) {
        return userMapper.queryEmailByAccount(account);
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
    public boolean checkPhoneByAccount(String account, String phone) {
        return userMapper.queryPhoneByAccount(account).equals(phone);
    }

    @Override
    public boolean checkPhoneTrue(String phoneNumber) {
        return userMapper.checkPhoneTrue(phoneNumber) == 1;
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
        return sendCode(phone);
    }

    @Override
    public int sendMailCode(String email) {
        int code = Random.getRandomPhoneCode4();
        Map<String,Integer> map = new HashMap<>();
        map.put("code",code);
        redisTemplate.opsForValue().set(email,map.get("code"),65, TimeUnit.SECONDS);
        return mailService.code(email, code);
    }

    @Override
    public int sendCode(String phone) {
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

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class, IllegalArgumentException.class})
    public int addUser(User user){
        UserVerification userVerification = new UserVerification(null,user.getAccount(),"未核验","未核验");
        return userMapper.addUser(user) & userMapper.addVerification(userVerification);
    }

    public int updateUser(User user){
        return userMapper.updateUser(user);
    }

    @Override
    public int checkCodeAndUpdateUser(int code, User user) {
        String phone = "";
//        System.out.println("+++++++++++++"+user);
        if(user.getPhone_number() == null){
            phone = userMapper.queryPhoneByAccount(user.getAccount());
        }else{
            //新手机号(修改手机操作)
            phone = user.getPhone_number();
        }
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

    @Override
    public int checkCodeAndUpdateUserByPhone(int code, User user) {
        String phone = user.getPhone_number();
//        System.out.println("+++++++++++++"+user);
        if(!redisTemplate.hasKey(phone)){
            return -2;
        }
        int redisCode = (int) redisTemplate.opsForValue().get(phone);
        if(code == redisCode){
            return userMapper.updateUserByPhone(user);
        }else{
            return -1;
        }
    }

    @Override
    public int checkMailCodeAndUpdateUser(int code, User user) {
        if(Boolean.FALSE.equals(redisTemplate.hasKey(user.getEmail()))){
            return -2;
        }
        int redisCode = (int) redisTemplate.opsForValue().get(user.getEmail());
        if(code == redisCode){
            return userMapper.updateUser(user);
        }else{
            return -1;
        }
    }

    public int deleteUserById(Integer id){
        return userMapper.deleteUserById(id);
    }

    @Override
    public int delUserByAccount(String account) {
        return userMapper.delUserByAccount(account);
    }

    @Override
    public int batchDelUserByAccount(List<String> accounts) {
        return userMapper.batchDelUserByAccount(accounts);
    }
}
