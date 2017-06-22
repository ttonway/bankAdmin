package com.psbc.util;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by ttonway on 2017/6/8.
 */
public class MD5PasswordEncoder implements PasswordEncoder {
    private static Logger logger = Logger.getLogger(MD5PasswordEncoder.class);

    @Override
    public String encode(CharSequence rawPassword) {
        String md5 = "";
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(rawPassword.toString().getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            md5 = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            logger.error("encode fail.", e);
        }
        return md5;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
