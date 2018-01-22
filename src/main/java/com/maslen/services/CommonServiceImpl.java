package com.maslen.services;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.services.interfaces.CommonService;
import com.maslen.utils.Aes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommonServiceImpl implements CommonService {
    private final UserDao userDao;
    private final BCryptPasswordEncoder encoder;

    private static final String ENCRYPT_KEY = "Why_did_you_do_that?";

    @Autowired
    public CommonServiceImpl(UserDao userDao, BCryptPasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    @Override
    public String encryptPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public String decrypt(String parameter) {
        return Aes.decrypt(parameter, ENCRYPT_KEY);
    }

    @Override
    public String encrypt(String parameter) {
        return Aes.encrypt(parameter, ENCRYPT_KEY);
    }
}
