package com.jie.service.Impl;

import com.jie.mapper.AdminMapper;
import com.jie.pojo.Admin;
import com.jie.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/7/20 15:08
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminMapper adminMapper;

    @Override
    public List<Admin> queryAdminPaging(String start, String count, String key, String value) {
        return adminMapper.queryAdminPaging(start, count, key, value);
    }

    @Override
    public int queryAdminCount(String key, String value) {
        return adminMapper.queryAdminCount(key, value);
    }

    @Override
    public int loginCheck(String account, String password) {
        if(adminMapper.queryAccount(account) == 0){
            //账号不存在
            return 0;
        }
        if(adminMapper.queryPassword(account).equals(password)){
            //密码正确
            return 1;
        }
        //密码错误
        return -1;
    }

    @Override
    public Admin login(String account) {
        return adminMapper.queryAdminByAccount(account);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }
}
