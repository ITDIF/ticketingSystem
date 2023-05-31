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

    User queryUserById(Integer id);

    User queryUserByNumber(long number);

    String queryQQByIdNumber(String id_number);

    String queryUsernameByAccount(String account);

    int queryAccount(String account);
    User queryUserByAccount(String account);
    int addUser(User user);

    int updateUser(User user);

    int deleteUserById(Integer id);
}
