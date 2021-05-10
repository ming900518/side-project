package com.blog.util;

public class BinaryUtil {
	
	public static short combindTwoBytes(byte one, byte two){
		return (short) ((one << 8) | (two & 0xFF));
	}
	
	
	
}
