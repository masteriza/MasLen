package com.maslen.dao.interfaces;

import com.maslen.models.User;

public interface UserDao {

    User addUser(User user);

    User getUserByEmail(String email);

    boolean isRegisteredEmail(String email);

    boolean isRegisteredEmailAndActivated(String email);

    boolean isRegisteredPhone(String phone);

    boolean activateUser(String email);
}
