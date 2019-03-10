package com.soecode.lyf.mapper;

import com.soecode.lyf.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 用户登录判断
     * @param user
     * @return
     */
    int userLogin(User user);

    /**
     * 通过token签名获取用户信息
     * @param token
     * @return
     */
    User getToken(String token);

    /**
     * 判断注册的用户名是否存在
     * @param username
     * @return
     */
    int userJudge(int username);

    /**
     * 用户注册
     * @param user
     * @return
     */
    int userRegister(User user);

    /**
     * 通过where查询用户表
     * @param where
     * @return
     */
    List<Map<String,Object>> selectAllByWhere(@Param("where")String where);
}