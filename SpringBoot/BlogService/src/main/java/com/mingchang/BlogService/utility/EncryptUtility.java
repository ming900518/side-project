package com.mingchang.BlogService.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtility {

    public static MessageDigest md5;
    private static final int PAD_BELOW = 0x10;
    private static final int TWO_BYTES = 0xFF;

    public static String convertMD5(String beforValue) throws NoSuchAlgorithmException {
        try {
            md5 = MessageDigest.getInstance("MD5");
            String afterValue = "";
            md5.update(beforValue.getBytes());
            byte[] array = md5.digest();
            StringBuffer sb = new StringBuffer(32);
            for (int j = 0; j < array.length; ++j) {
                int b = array[j] & TWO_BYTES;
                if (b < PAD_BELOW){
                    sb.append('0');
                }
                sb.append(Integer.toHexString(b));
            }
            afterValue = sb.toString();
            return afterValue;
        } catch (NoSuchAlgorithmException ex) {
            LogUtility.error(EncryptUtility.class, "convertMD5 has error : " + ex);
            throw ex;
        }
    }

    public static String digest(String s) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(s.getBytes());
        byte[] digestBytes = digest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte digestByte : digestBytes) {
            String hex = Integer.toHexString(0xFF & digestByte);
            while (hex.length() < 2)
                hex = "0" + hex;
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
