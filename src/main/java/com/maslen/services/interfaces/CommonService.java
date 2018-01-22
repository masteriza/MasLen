package com.maslen.services.interfaces;

public interface CommonService {

    String encryptPassword(String rawPassword);

    String decrypt(String parameter);

    String encrypt(String parameter);
}
