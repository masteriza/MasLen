package com.maslen.dao.Inf;

import com.maslen.models.User;

import java.util.Optional;

public interface UserDao {
    User addUser(User user);

    User findUserById(int userId);

    long isRegisteredEmail(String email);

    long isRegisteredPhone(long phone);

    Optional<User> findUserByUsername(String uesrname);
}
