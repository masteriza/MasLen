package com.maslen.dao.Inf;

import com.maslen.models.User;

public interface UserDao {
    User addUser(User user);

    User findUserById(int userId);

    long isRegisteredEmail(String email);
}
