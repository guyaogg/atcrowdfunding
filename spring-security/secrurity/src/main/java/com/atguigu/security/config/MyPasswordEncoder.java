package com.atguigu.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Objects;

/**
 * @author guyao
 * @create 2021-08-07 15:55
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {
    // 加密方式
    final static  String ENCODE_STYLE="MD5";
    // 加密方法
    @Override
    public String encode(CharSequence rawPassword) {
        // 创建MessageDigest对象
        try {
            MessageDigest digest = MessageDigest.getInstance(ENCODE_STYLE);
            // 获取rawPassword的字节数组
            byte[] input = ((String) rawPassword).getBytes();
            // 加密
            byte[] output = digest.digest(input);
            // 转化为16进制符对应的字符
            String encoded = new BigInteger(1, output).toString(16).toUpperCase();
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    // 验证
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        对表单明文加密
        String formPassword = encode(rawPassword);
        // 声明数据库密码
        String databasePassword = encodedPassword;
        // 比较
        boolean result = Objects.equals(formPassword, databasePassword);
        // 返回对比结果
        return result;
    }
}
