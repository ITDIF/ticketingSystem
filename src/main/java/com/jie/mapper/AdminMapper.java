package com.jie.mapper;

import com.jie.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/7/20 14:47
 */
@Mapper
@Repository
public interface AdminMapper {
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
     * 查询账户是否存在
     * @param account 账户
     * @return 结果
     */
    int queryAccount(String account);

    /**
     * 查询账号密码
     * @param account 账号
     * @return 密码
     */
    String queryPassword(String account);

    /**
     * 根据账号查询管理员信息
     * @param account 账号
     * @return 管理员信息
     */
    Admin queryAdminByAccount(String account);

}
