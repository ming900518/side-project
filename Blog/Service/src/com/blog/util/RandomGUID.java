package com.blog.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Random;

public class RandomGUID {
	public static String valueBeforeMD5 = "";  
	public static String valueAfterMD5 = "";  
	private static Random myRand;
	private static SecureRandom mySecureRand; 
	private static String s_id;
	
	static {  
		mySecureRand = new SecureRandom();  
		long secureInitializer = mySecureRand.nextLong();  
		myRand = new Random(secureInitializer);  
		try {  
			s_id = InetAddress.getLocalHost().toString();  
		} catch (UnknownHostException e) {  
			e.printStackTrace();  
		}
	}

	public RandomGUID() {  
	      getRandomGUID(false);  
	}  
	
	public RandomGUID(boolean secure) {  
	      getRandomGUID(secure);  
	}
	
	private void getRandomGUID(boolean secure) {  
		StringBuffer sbValueBeforeMD5 = new StringBuffer(128);  
	  
		try {  
			long time = System.currentTimeMillis();  
			long rand = 0;  
			if (secure) {  
	           rand = mySecureRand.nextLong();  
	        } else {  
	           rand = myRand.nextLong();  
	        }  
	        sbValueBeforeMD5.append(s_id);  
	        sbValueBeforeMD5.append(":");  
	        sbValueBeforeMD5.append(Long.toString(time));  
	        sbValueBeforeMD5.append(":");  
	        sbValueBeforeMD5.append(Long.toString(rand));  
	  
	        valueBeforeMD5 = sbValueBeforeMD5.toString();
	        valueAfterMD5 = EncryptUtil.convertMD5(valueBeforeMD5);  
		} catch (Exception ex){
	    	  LogUtils.error(getClass(), "construct getRandomGUID has error : " +ex);
		}  
	}  
	
	public String getGUID() {  
		String raw = valueAfterMD5.toUpperCase();  
	    StringBuffer sb = new StringBuffer(64);  
	    sb.append(raw.substring(0, 8));  
	    sb.append("-");  
	    sb.append(raw.substring(8, 12));  
	    sb.append("-");  
	    sb.append(raw.substring(12, 16));  
	    sb.append("-");  
	    sb.append(raw.substring(16, 20));  
	    sb.append("-");  
	    sb.append(raw.substring(20));
	    return sb.toString();  
	}  
	
}
