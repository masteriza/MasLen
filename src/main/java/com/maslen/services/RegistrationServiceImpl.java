package com.maslen.services;

import com.maslen.models.*;
import com.maslen.services.interfaces.RegistrationService;
import com.maslen.services.interfaces.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Date;

@Component
public class RegistrationServiceImpl implements RegistrationService {

    private static final String EMAIL_NOT_UNIQUE = "Email is not unique!";
    private static final String PASSWORDS_NOT_MATCH = "Passwords do not match!";
    private static final String PHONE_NOT_UNIQUE = "Phone is not unique!";

    private final VerificationService verificationService;

    @Autowired
    public RegistrationServiceImpl(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public BindingResult validateForm(RegistrationUserDto registrationUserDto, BindingResult bindingResult) {
        if (verificationService.isRegisteredEmail(registrationUserDto.getEmail())) {
            bindingResult.rejectValue("email", "error.user.email.nonUnique", EMAIL_NOT_UNIQUE);
        }
        if (!verificationService.isPasswordMatch(registrationUserDto.getRawPassword(), registrationUserDto.getRepeatRawPassword(), bindingResult)) {
            bindingResult.rejectValue("rawPassword", "error.user.password.missMatch", PASSWORDS_NOT_MATCH);
        }
        if (verificationService.isRegisteredPhone(registrationUserDto.getPhone())) {
            bindingResult.rejectValue("phone", "error.user.phone.nonUnique", PHONE_NOT_UNIQUE);
        }
        return bindingResult;
    }

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
