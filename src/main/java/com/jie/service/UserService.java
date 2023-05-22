package com.jie.service;

import com.jie.pojo.User;

import java.util.List;

/**
 * @author jie
 */
public interface UserService {
    /**
     *判断账户是否存在
     * @param account 账号（账户名/手机号）
     * @param password 密码
     * @return
     */
    String loginCheck(String account, String password);

    /**
     * 查询登录成功的用户信息
     * @param account 账户
     * @return user对象
     */
    User login(String account);
    List<User> queryUserList();

    boolean queryAccount(String account);
    User queryUserById(Integer id);

    /**
     * 通过号码查询对象
     * @param number 号码
     * @return user对象
     */
    User queryUserByNumber(long number);

    /**
     * 通过账号查询对象
     * @param account 账号
     * @return user对象
     */
    User queryUserByAccount(String account);

    int addUser(User user);

    int updateUser(User user);

    int deleteUserById(Integer id);

}
