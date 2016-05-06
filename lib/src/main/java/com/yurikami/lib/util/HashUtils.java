package com.yurikami.lib.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * hash工具类
 */
public class HashUtils {

    private static String getDigest(String source, String hashType) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance(hashType);
            for (byte b : md.digest(source.getBytes("UTF-8"))) {
                //在32位的电脑中数字都是以32格式存放的，如果是一个byte(8位)类型的数字，他的高24位里面都是随机数字，低8位
                //才是实际的数据。java.lang.Integer.toHexString() 方法的参数是int(32位)类型，如果输入一个byte(8位)类型的数字，这个
                //方法会把这个数字的高24为也看作有效位，这就必然导致错误，使用& 0XFF操作，可以把高24位置0以避免这样错误
                //的发生。
                sb.append(Integer.toHexString(0xFF & b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("no such algorithm");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("no such encoding");
        }
    }


    /** 根据{@code source} 获取16进制md5的hash字符串（小写）*/
    public static String md5(String source) {
        return getDigest(source, "MD5");
    }

    /** 根据{@code source} 获取16进制sha1的hash字符串（小写）*/
    public static String sha1(String source) {
        return getDigest(source, "SHA1");
    }

}
