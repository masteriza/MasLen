package com.maslen.dao.interfaces;

import com.maslen.models.User;

import java.util.Optional;

public interface UserDao {
    User addUser(User user);

    User findUserById(int userId);

    Optional<User> searchUserByEmail(String username);

    long isRegisteredEmail(String email);

    long isRegisteredEmailAndActivated(String email);

    long isRegisteredPhone(String phone);

    User activateUser(String email);
}
