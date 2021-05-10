package com.blog.db.operatinglog.dao;

import java.util.List;
import java.util.Map;

import com.blog.api_backend.model.response.OperatingLogView;
import com.blog.db.operatinglog.model.OperatingLog;

public interface OperatingLogMapper {
    int deleteByPrimaryKey(Integer operatingLogId);

    int insert(OperatingLog record);

    int insertSelective(OperatingLog record);

    OperatingLog selectByPrimaryKey(Integer operatingLogId);

    int updateByPrimaryKeySelective(OperatingLog record);

    int updateByPrimaryKey(OperatingLog record);
    
    List<OperatingLogView> queryLogList(Map<String, Object> paramMap);
}