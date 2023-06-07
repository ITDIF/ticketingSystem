package com.jie.Test;

import com.jie.pojo.User;
import com.jie.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Test
    public void query(){
//        System.out.println(userService.queryUserList());
        System.out.println(userService.queryAccount("aaaaaa"));
    }
    @Test
    public void queryUserById(){
        System.out.println(userService.queryUserById(1));
    }

    @Test
    public void queryUser(){
//        System.out.println(userService.queryUserByNumber(10000)==null);
        System.out.println(userService.queryUserByAccount("s123"));
    }
    @Test
    public void addUser(){
        User user = new User(null,"aaaaaa","3333","ga33o",333,"333","333","333","333@qq.com",new Timestamp(new Date().getTime()));
        try {
            userService.addUser(user);
        }catch (Exception e){
            System.out.println("注册失败！");
        }
//        System.out.println();
    }
    @Test
    public void deleteUserById(){
        System.out.println(userService.deleteUserById(4));
    }
}


