package com.lyj.utils;

import java.util.Random;

public class ValidateCodeUtil {
    /**
     * 产生一个随机的四位验证码
     * @return 返回一个String类型的验证码
     */
    public static String createValidateCode(int length) {
        Random ran = new Random();
        char[] chars = "abcdefghijkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXY123456789".toCharArray();
        StringBuilder str=new StringBuilder();
        for(int i = 0; i < length; i++){
            str.append(chars[ran.nextInt(chars.length)]);
        }
        return str.toString();
    }
}
