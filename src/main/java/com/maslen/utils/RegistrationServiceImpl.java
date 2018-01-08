package com.maslen.utils;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.models.*;
import com.maslen.utils.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Date;

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
        if (isRegisteredEmail(registrationUserDto.getEmail())) {
            bindingResult.rejectValue("email", "error.user.email.nonUnique", EMAIL_NOT_UNIQUE);
        }
        if (!isPasswordMatch(registrationUserDto.getRawPassword(), registrationUserDto.getRepeatRawPassword(), bindingResult)) {
            bindingResult.rejectValue("rawPassword", "error.user.password.missMatch", PASSWORDS_NOT_MATCH);
        }
        if (isRegisteredPhone(registrationUserDto.getPhone())) {
            bindingResult.rejectValue("phone", "error.user.phone.nonUnique", PHONE_NOT_UNIQUE);
        }
        if (bindingResult.hasErrors()) {
            isValidData = false;
        }
        return isValidData;
    }

    @Override
    public boolean isRegisteredEmail(String email) {
        return userDao.isRegisteredEmail(email);
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String repeatRawPassword, BindingResult bindingResult) {
        return rawPassword.equals(repeatRawPassword);
    }


    @Override
    public boolean isRegisteredPhone(String phone) {
        return userDao.isRegisteredPhone(phone);
    }

    @Override
    public String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public User userDtoToUser(RegistrationUserDto registrationUserDto) {
        Phone phone = new Phone();
        phone.setNumber(registrationUserDto.getPhone());

        Person person = new Person();
        person.setFirstname(registrationUserDto.getFirstName());
        person.setLastname(registrationUserDto.getLastName());
        person.setMiddlename(registrationUserDto.getMiddleName());
        person.setSex(registrationUserDto.getSex());
        person.setBirthday(registrationUserDto.getBirthday());
        person.setPhone(phone);
        person.setStatus("I".charAt(0));

//        Person person = Person.builder()
//                .firstname(registrationUserDto.getFirstName())
//                .lastname(registrationUserDto.getLastName())
//                .middlename(registrationUserDto.getMiddleName())
//                .sex(registrationUserDto.getSex())
//                .birthday(registrationUserDto.getBirthday())
//                .phone(phone)
//                .status("I".charAt(0)).build();

//        ArrayList<Authority> authorities = new ArrayList<>();
//        authorities.add(Authority.builder().name(AuthorityName.ROLE_USER).build());

        User user = new User();
        user.setUsername(registrationUserDto.getFirstName() + " " + registrationUserDto.getLastName());
        user.setPassword(registrationUserDto.getRawPassword());
        user.setEmail(registrationUserDto.getEmail());
        user.setEnabled(true);
        user.setIsActivated(false);
        user.setLastPasswordResetDate(new Date());
        Authority authority = new Authority();
        authority.setAuthorityId(Long.valueOf(1));
        authority.setName(AuthorityName.ROLE_USER);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        authority.setUsers(users);

        ArrayList<Authority> authorities = new ArrayList<>();
//        authorities.add(Authority.builder().name(AuthorityName.ROLE_USER).build());
        authorities.add(authority);
        user.setAuthorities(authorities);
//        user.getAuthorities().add(authority);

//        user.getAuthorities().add(Authority.builder().authorityId((long) 1).name(AuthorityName.ROLE_USER).build());
        user.setPerson(person);

        return user;

        //        Role role = new Role();
//        role.setRoleName("USER");


//        return User.builder()
//                .username(registrationUserDto.getFirstName() + " " + registrationUserDto.getLastName())
//                .password(registrationUserDto.getRawPassword())
//                .email(registrationUserDto.getEmail())
//                .enabled(true)
//                .isActivated(false)
//                .lastPasswordResetDate(new Date())
//                .authorities(authorities)
//                .person(person)

//                .sex(registrationUserDto.getSex().charAt(0))
//                .birthday(registrationUserDto.getBirthday())
//                .phone(phone)
//                .role(role)
//                .status("I".charAt(0))
//                .isActivated(false)
//                .build();
    }


}
