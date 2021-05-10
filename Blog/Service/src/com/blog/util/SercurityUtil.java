package com.blog.util;

import java.util.UUID;

public class SercurityUtil{

	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}
	
	public static String getGUID() {  
		RandomGUID randomGUID = new RandomGUID();
		return randomGUID.getGUID();
	}  

}

