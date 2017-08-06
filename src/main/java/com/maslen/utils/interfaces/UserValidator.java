package com.maslen.utils.interfaces;

public interface UserValidator {
    boolean isRegisteredEmail(String email);

    boolean isRegisteredPhone(long phone);

    boolean isPasswordMatch(String rawPassword, String repeatPassword);


}
