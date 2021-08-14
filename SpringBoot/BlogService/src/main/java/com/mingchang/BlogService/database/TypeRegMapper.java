package com.mingchang.BlogService.database;

import com.mingchang.BlogService.model.database.TypeReg;

public interface TypeRegMapper {
    int deleteByPrimaryKey(Integer typeRegId);

    int insert(TypeReg record);

    int insertSelective(TypeReg record);

    TypeReg selectByPrimaryKey(Integer typeRegId);

    int updateByPrimaryKeySelective(TypeReg record);

    int updateByPrimaryKey(TypeReg record);
}