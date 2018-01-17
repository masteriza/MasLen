package com.maslen.services;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.models.*;
import com.maslen.services.interfaces.RegistrationService;
import com.maslen.services.interfaces.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Date;

@Component
public class RegistrationServiceImpl implements RegistrationService {

    private static final String EMAIL_NOT_UNIQUE = "Email is not unique!";
    private static final String PASSWORDS_NOT_MATCH = "Passwords do not match!";
    private static final String PHONE_NOT_UNIQUE = "Phone is not unique!";

    private final UserDao userDao;
    private final BCryptPasswordEncoder encoder;
    private final VerificationService verificationService;


    @Autowired
    public RegistrationServiceImpl(UserDao userDao, BCryptPasswordEncoder encoder, VerificationService verificationService) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.verificationService = verificationService;
    }


    @Override
    public boolean validateForm(RegistrationUserDto registrationUserDto, BindingResult bindingResult) {
        boolean isValidData = true;
        if (verificationService.isRegisteredEmail(registrationUserDto.getEmail())) {
            bindingResult.rejectValue("email", "error.user.email.nonUnique", EMAIL_NOT_UNIQUE);
        }
        if (!verificationService.isPasswordMatch(registrationUserDto.getRawPassword(), registrationUserDto.getRepeatRawPassword(), bindingResult)) {
            bindingResult.rejectValue("rawPassword", "error.user.password.missMatch", PASSWORDS_NOT_MATCH);
        }
        if (verificationService.isRegisteredPhone(registrationUserDto.getPhone())) {
            bindingResult.rejectValue("phone", "error.user.phone.nonUnique", PHONE_NOT_UNIQUE);
        }
        if (bindingResult.hasErrors()) {
            isValidData = false;
        }
        return isValidData;
    }

//    @Override
//    public boolean isRegisteredEmail(String email) {
//        return userDao.isRegisteredEmail(email);
//    }
//
//    @Override
//    public boolean isPasswordMatch(String rawPassword, String repeatRawPassword, BindingResult bindingResult) {
//        return rawPassword.equals(repeatRawPassword);
//    }
//
//    @Override
//    public boolean isRegisteredPhone(String phone) {
//        return userDao.isRegisteredPhone(phone);
//    }
//
//    @Override
//    public String encodePassword(String rawPassword) {
//        return encoder.encode(rawPassword);
//    }

    @Override
    public User userDtoToUser(RegistrationUserDto registrationUserDto) {
        Person person = Person.builder()
                .firstname(registrationUserDto.getFirstName())
                .lastname(registrationUserDto.getLastName())
                .middlename(registrationUserDto.getMiddleName())
                .sex(registrationUserDto.getSex())
                .birthday(registrationUserDto.getBirthday())
                .phone(Phone.builder().number(registrationUserDto.getPhone()).build())
                .status("I".charAt(0)).build();

        return User.builder()
                .username(registrationUserDto.getFirstName() + " " + registrationUserDto.getLastName())
                .password(registrationUserDto.getRawPassword())
                .email(registrationUserDto.getEmail())
                .enabled(true)
                .isActivated(false)
                .lastPasswordResetDate(new Date())
                .authorities(Arrays.asList(Authority.builder().name(AuthorityName.ROLE_USER).build()))
                .person(person)
                .build();
    }
}
