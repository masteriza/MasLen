package com.maslen.utils;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.models.Phone;
import com.maslen.models.RegistrationUserDto;
import com.maslen.models.Role;
import com.maslen.models.User;
import com.maslen.utils.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class RegistrationServiceImpl implements RegistrationService {

    private static final String EMAIL_NOT_UNIQUE = "Email is not unique!";
    private static final String PASSWORDS_NOT_MATCH = "Passwords do not match!";
    private static final String PHONE_NOT_UNIQUE = "Phone is not unique!";

    private final UserDao userDao;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public RegistrationServiceImpl(UserDao userDao, BCryptPasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }


    @Override
    public boolean validateForm(RegistrationUserDto registrationUserDto, BindingResult bindingResult) {
        boolean isValidData = true;
        if (!isRegisteredEmail(registrationUserDto.getEmail())) {
            bindingResult.rejectValue("email", "error.user.email.nonUnique", EMAIL_NOT_UNIQUE);
        }
        if (!isPasswordMatch(registrationUserDto.getRawPassword(), registrationUserDto.getRepeatRawPassword(), bindingResult)) {
            bindingResult.rejectValue("rawPassword", "error.user.password.missMatch", PASSWORDS_NOT_MATCH);
        }
        if (!isRegisteredPhone(registrationUserDto.getPhone())) {
            bindingResult.rejectValue("phone", "error.user.phone.nonUnique", PHONE_NOT_UNIQUE);
        }
        if (bindingResult.hasErrors()) {
            isValidData = false;
        }
        return isValidData;
    }

    @Override
    public boolean isRegisteredEmail(String email) {
        return userDao.isRegisteredEmail(email) == 0;
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String repeatRawPassword, BindingResult bindingResult) {
//        if (!rawPassword.equals(repeatRawPassword)) {
//            bindingResult.rejectValue("rawPassword", "error.user.password.missMatch");
//        }
        return rawPassword.equals(repeatRawPassword);
    }


    @Override
    public boolean isRegisteredPhone(String phone) {
        return userDao.isRegisteredPhone(phone) == 0;
    }

    @Override
    public String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public User userDtoToUser(RegistrationUserDto registrationUserDto) {
        Phone phone = new Phone();
        phone.setNumber(registrationUserDto.getPhone());

        Role role = new Role();
        role.setRoleName("USER");
        return User.builder()
                .email(registrationUserDto.getEmail())
                .password(registrationUserDto.getRawPassword())
                .sex(registrationUserDto.getGender().charAt(0))
                .birthday(registrationUserDto.getBirthday())
                .phone(phone)
                .role(role)
                .status("I".charAt(0))
                .isActivated(false)
                .build();
    }


}
