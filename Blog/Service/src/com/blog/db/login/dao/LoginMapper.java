package com.blog.db.login.dao;

import com.blog.db.login.model.Login;

public interface LoginMapper {
    int deleteByPrimaryKey(Integer loginId);

    int insert(Login record);

    int insertSelective(Login record);

    Login selectByPrimaryKey(Integer loginId);

    int updateByPrimaryKeySelective(Login record);

    int updateByPrimaryKey(Login record);
}