package com.maslen.dao.interfaces;


import com.maslen.models.User;

public interface UserDao {
    User addUser(User user);

    User findUserById(int userId);

    User getUserByEmail(String username);

    long isRegisteredEmail(String email);

    long isRegisteredEmailAndActivated(String email);

    long isRegisteredPhone(String phone);

    int activateUser(String email);
}
