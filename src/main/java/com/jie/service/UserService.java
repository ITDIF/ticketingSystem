package com.jie.service;

import com.jie.pojo.User;
import com.jie.pojo.UserMoneyIntegral;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author jie
 */
public interface UserService {
    /**
     * 分页查询用户
     * @param start 开始
     * @param count 每次查询数量
     * @param key 字段
     * @param value 字段值
     * @return 用户集合
     */
    List<User> queryUserPaging(String start, String count, String key, String value);

    /**
     * 用户数量
     * @param key 字段
     * @param value 字段值
     * @return 数量
     */
    int queryUserCount(String key, String value);
    /**
     *判断账户是否存在
     * @param account 账号（账户名/手机号）
     * @param password 密码
     * @return
     */
    Map<String, Object> loginCheck(String account, String password);

    int loginPhoneCodeCheck(String code, String phone);
    /**
     * 通过账号查询两个表的部分信息（数据展示）
     * @param account 账号
     * @return 信息
     */
    Map<String, String> queryUserInfoByAccount(String account);

    /**
     * 查询登录成功的用户信息
     * @param account 账户
     * @return user对象
     */
    User login(String account);
    String userEmail(String account);
    List<User> queryUserList();

    int queryIdNumberByAccount(String account);
    /**
     * 确认手机号
     * @param account 账户
     * @param phone 手机号
     * @return 是否一致
     */
    boolean checkPhoneByAccount(String account, String phone);
    /**
     * 确认手机号已注册
     * @param phoneNumber 手机号
     * @return
     */
    boolean checkPhoneTrue(String phoneNumber);

    boolean queryAccount(String account);
    User queryUserById(Integer id);

    /**
     * 通过号码查询对象
     * @param number 号码
     * @return user对象
     */
    User queryUserByNumber(String number);

    /**
     * 通过账号查询对象
     * @param account 账号
     * @return user对象
     */
    User queryUserByAccount(String account);

    UserMoneyIntegral queryUserMoneyAndIntegralByAccount(String account);

    /**
     * 确认密码是否一致
     * @param account 账户
     * @param password 密码
     * @return boolean
     */
    boolean checkPass(String account, String password);

    /**
     * 发送手机验证码通过账户
     * @param account 账户
     * @return int
     */
    int sendPhoneCode(String account);
    /**
     * 发送邮箱验证码
     * @param email 邮箱
     * @return int
     */
    int sendMailCode(String email);
    /**
     * 给手机号码发送手机验证码
     * @param phone 手机号
     * @return int
     */
    int sendCode(String phone);

    String queryUsernameByAccount(String account);

    int addUser(User user);

    /**
     * 修改用户信息
     * @param user 用户
     * @return int
     */
    int updateUser(User user);

    /**
     * 余额和积分修改
     * @param account 账户
     * @param money 余额
     * @param integral 积分
     * @return
     */
    int updateMoneyAndIntegral(String account, BigDecimal money, int integral);

    /**
     * 修改密码（先确认手机验证码）
     * @param code 验证码
     * @param user 用户
     * @return 结果
     */
    int checkCodeAndUpdateUser(int code,User user);
    /**
     * 修改密码（先确认手机验证码）(手机号)
     * @param code 验证码
     * @param user 用户
     * @return 结果
     */
    int checkCodeAndUpdateUserByPhone(int code,User user);
    /**
     * 修改邮箱（先确认邮箱验证码）
     * @param code 验证码
     * @param user 用户
     * @return 结果
     */
    int checkMailCodeAndUpdateUser(int code,User user);

    int deleteUserById(Integer id);
    /**
     * 删除用户
     * @param account 账号
     * @return 结果
     */
    int delUserByAccount(String account);
    /**
     * 批量删除用户
     * @param accounts 账号列表
     * @return 结果
     */
    int batchDelUserByAccount(List<String> accounts);

}
