package com.maslen.services.interfaces;

import org.springframework.validation.BindingResult;

public interface VerificationService {

    boolean isRegisteredEmail(String email);

    boolean isRegisteredPhone(String phone);

    boolean isPasswordMatch(String rawPassword, String repeatPassword, BindingResult bindingResult);

//    String encryptPassword(String rawPassword);

//    String decrypt(String parameter);

    boolean isConfirmationEmail(String email);

    boolean isValidSessionForEmail(String email, String session);

}
