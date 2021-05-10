package com.blog.util;

public class StringUtils {
	
	/**
	 * 全形轉半形
	 * @param source
	 * @return
	 */
	public static String convertToHalfWidth(final String source) {
		if (null == source) {
			return null;
		}
		
		char[] charArray = source.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			int ic = (int) charArray[i];
			
			if (ic >= 65281 && ic <= 65374) {
				charArray[i] = (char) (ic - 65248);
			} else if (ic == 12288) {
				charArray[i] = (char) 32;
			}
		}
		return new String(charArray);
	}
	
	/**
	 * 半形轉全形
	 * @param source
	 * @return
	 */
	public static String convertToFullWidth(final String source) {
		if (null == source) {
			return null;
		}
		
		char[] charArray = source.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			int ic = (int) charArray[i];
			
			if (ic >= 33 && ic <= 126) {
				charArray[i] = (char) (ic + 65248);
			} else if (ic == 32) {
				charArray[i] = (char) 12288;
			}
		}
		return new String(charArray);
	}
	
}
