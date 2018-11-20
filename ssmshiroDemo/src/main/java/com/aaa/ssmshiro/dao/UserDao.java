package com.aaa.ssmshiro.dao;

import com.aaa.ssmshiro.entity.User;

import java.util.List;

/**
 * className:UserDao
 * discriptoin:
 * author:zz
 * createTime:2018-11-15 22:04
 */
public interface UserDao {

    //登录验证
    User login(User user);

    /**
     *根据username查询角色
     */
    List<String> selectRole(String username);

    /**
     * 根据username查询角色权限
     */
    List<String> selectPermission(String username);
}
