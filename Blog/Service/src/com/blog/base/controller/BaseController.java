package com.blog.base.controller;

import java.util.List;

import com.blog.base.model.BaseModel;

public class BaseController {
	
	public BaseModel basicOutput(Object obj){
		BaseModel baseModel = new BaseModel();
		if(obj !=null){
			baseModel.setResult(true);
			baseModel.setData(obj);
		}else{
			baseModel.setResult(false);
		}
		return baseModel;
	}
	
	public BaseModel basicOutput(List<?> list){
		BaseModel baseModel = new BaseModel();
		if(list.size()>0){
			baseModel.setResult(true);
			baseModel.setData(list);
		}else{
			baseModel.setResult(true);
			baseModel.setData(list);
		}
		return baseModel;
	}
	
	public BaseModel basicOutput(String successMsg,String errorMsg,Object obj){
		BaseModel baseModel = new BaseModel();
		if(obj !=null){
			baseModel.setResult(true);
			baseModel.setMessage(successMsg);
			baseModel.setData(obj);
		}else{
			baseModel.setResult(false);
			baseModel.setMessage(errorMsg);
		}
		return baseModel;
	}
	
}
