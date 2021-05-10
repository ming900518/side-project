package com.blog.util;

import java.math.BigDecimal;
import java.util.List;

public class ValidateUtil {
	
	public static boolean isEmpty(Object obj){
		if(obj == null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(Object obj){
		if(obj != null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmptyAndSize(List<?> list){
		if(list != null && list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isBlank(String str){
		if(str ==null || "".equals(str)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotBlank(String str){
		if(str !=null && !"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isNotNumNoneInteger(Integer num){
		if(num !=null && num != 0){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotNumNone(Integer num){
		if(num !=null && num != 0){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotNumNone(BigDecimal num){
		if(num !=null && BigDecimal.ZERO.compareTo(num) !=0){
			return true;
		}else{
			return false;
		}
	}
	
}
