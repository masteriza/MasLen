package com.maslen.dao.interfaces;

import com.maslen.models.User;

import java.util.Optional;

public interface UserDao {
    User addUser(User user);

    User findUserById(int userId);

    Optional<User> findUserByUsername(String uesrname);

    long isRegisteredEmail(String email);

    long isRegisteredPhone(String phone);
}
