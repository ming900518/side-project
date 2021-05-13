package com.blog.db.content.dao;

import com.blog.api_backend.model.response.ContentListResponse;
import com.blog.db.content.model.Content;

import java.util.List;
import java.util.Map;

public interface ContentMapper {
    int deleteByPrimaryKey(Integer contentId);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Integer contentId);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);

    List<ContentListResponse> queryContentList(Map<String, Object> paramMap);
}