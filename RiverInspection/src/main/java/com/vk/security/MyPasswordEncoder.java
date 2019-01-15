package com.vk.security;

import com.vk.util.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by wj on 18-4-18.
 */
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence arg0) {
        return arg0.toString();
    }

    @Override
    public boolean matches(CharSequence arg0, String arg1) {
        return arg1.equals(MD5.getMD5(arg0.toString()));
    }
}
