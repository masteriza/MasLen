package com.maslen.config;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityConfigTest {
    @Test
    public void bCryptPasswordEncoder() throws Exception {
        System.out.println(new BCryptPasswordEncoder().encode("1"));
    }

}