package com.mingchang.BlogService.database;

import com.mingchang.BlogService.model.database.AdminInfo;
import com.mingchang.BlogService.model.request.LoginBackendRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminInfoMapper {

    int deleteByPrimaryKey(Integer adminId);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);

    AdminInfo inspectPwd(LoginBackendRequest loginBackendRequest);

}