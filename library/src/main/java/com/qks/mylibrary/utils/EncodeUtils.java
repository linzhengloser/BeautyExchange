package com.qks.mylibrary.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密相关工具类
 * Created by admin on 2016/3/30.
 */
public class EncodeUtils {

    /**
     * 使用MD5加密
     * @param str
     * @return
     */
    public static String md5(String str){
        byte[] hash = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length*2);
        for (byte b : hash){
            if((b & 0xFF)<0x10){
                hex.append("0");
            }
            hex.append(Integer.toHexString(b&0xFF));
        }
        return hex.toString();
    }



}
