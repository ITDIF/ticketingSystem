package com.jie.mapper;

import com.jie.pojo.User;
import com.jie.pojo.UserVerification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jie
 */
@Mapper
@Repository
public interface UserMapper {

    List<User> queryUserList();
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

    /**
     * 查询密码
     * @param account 账户
     * @return 密码
     */
    String queryPassword(String account);

    /**
     * 查手机号
     * @param account 账户
     * @return 手机号
     */
    String queryPhoneByAccount(String account);

    /**
     * 确认手机号已注册
     * @param phoneNumber 手机号
     * @return
     */
    int checkPhoneTrue(String phoneNumber);

    User queryUserById(Integer id);

    User queryUserByNumber(String number);

    String queryQQByIdNumber(String id_number);

    String queryEmailByAccount(String account);

    String queryUsernameByAccount(String account);

    int queryAccount(String account);
    User queryUserByAccount(String account);

    /**
     * 查询已有表名（table之后）
     * @param table 带查询表
     * @return 表名集合
     */
    List<String> queryTableName(String table);
    int addUser(User user);

    /**
     * 修改用户信息
     * @param user 用户
     * @return int
     */
    int updateUser(User user);
    /**
     * 修改用户信息(手机号)
     * @param user 用户
     * @return int
     */
    int updateUserByPhone(User user);
    int addVerification(UserVerification userVerification);

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
