package com.lyj.utils;

import org.springframework.util.DigestUtils;


/**
 * @author yanzhenwei
 *
 * MD5  ��һ�������㷨����������Ŀ��ʹ�ã��ر�������
 * MD5�޷��ƽ�
 */
public class MD5Utils {
    public static String getMD5(String oldStr) {
        if (oldStr == null || oldStr.equals("")) {
            return "";
        }
        String str = DigestUtils.md5DigestAsHex(oldStr.getBytes());
        String childStr = str.substring(7, 18);
        String salt = "lyj";
        String newStr = str + childStr + salt;

        return DigestUtils.md5DigestAsHex(newStr.getBytes());
    }

}
