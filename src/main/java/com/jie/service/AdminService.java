package com.jie.service;

import com.jie.pojo.Admin;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/7/20 15:08
 */
public interface AdminService {
    /**
     * 分页查询管理员
     * @param start 开始
     * @param count 每次查询数量
     * @param key 字段
     * @param value 字段值
     * @return 管理员集合
     */
    List<Admin> queryAdminPaging(String start, String count, String key, String value);

    /**
     * 管理员数量
     * @param key 字段
     * @param value 字段值
     * @return 数量
     */
    int queryAdminCount(String key, String value);
    /**
     * 登录
     * @param account 账号
     * @param password 密码
     * @return 结果
     */
    int loginCheck(String account, String password);

    /**
     * 通过账号查询管理员信息
     * @param account 账号
     * @return 管理员信息
     */
    Admin login(String account);
}
