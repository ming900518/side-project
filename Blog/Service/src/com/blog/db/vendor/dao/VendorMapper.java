package com.blog.db.vendor.dao;

import java.util.List;
import java.util.Map;

import com.blog.api_backend.model.response.VendorResponse;
import com.blog.db.vendor.model.Vendor;

public interface VendorMapper {
    int deleteByPrimaryKey(Integer vendorId);

    int insert(Vendor record);

    int insertSelective(Vendor record);

    Vendor selectByPrimaryKey(Integer vendorId);

    int updateByPrimaryKeySelective(Vendor record);

    int updateByPrimaryKey(Vendor record);

	List<VendorResponse> queryVendorList(Map<String, Object> paramMap);
}