package com.maslen.config;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityConfigTest {
    @Test
    public void bCryptPasswordEncoder() throws Exception {
//        for (int i = 0; i < 100000; i++) {
//            System.out.println(i + "    " + new BCryptPasswordEncoder().decrypt("111111"));
//        }
        System.out.println(new BCryptPasswordEncoder().encode("1111111"));
    }

}