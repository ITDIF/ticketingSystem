package com.jie.mapper;

import com.jie.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author jie
 */
@Mapper
@Repository
public interface UserMapper {

    List<User> queryUserList();

    /**
     * 通过账号查身份证号
     * @param account 账号
     * @return 身份证号
     */
    String queryIdNumberByAccount(String account);

    /**
     * 通过账号查询两个表的部分信息（数据展示）
     * @param account 账号
     * @return 信息
     */
    Map<String, String> queryUserInfoByAccount(String account);

    User queryUserById(Integer id);

    User queryUserByNumber(long number);

    String queryQQByIdNumber(String id_number);

    String queryUsernameByAccount(String account);

    int queryAccount(String account);
    User queryUserByAccount(String account);
    int addUser(User user);

    /**
     * 修改用户信息
     * @param user 用户
     * @return int
     */
    int updateUser(User user);

    int deleteUserById(Integer id);
}
