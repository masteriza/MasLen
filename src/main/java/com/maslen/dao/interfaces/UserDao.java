package com.maslen.dao.interfaces;


import com.maslen.models.User;

public interface UserDao {
    User addUser(User user);
//
//    User findUserById(int userId);

    User getUserByEmail(String email);

    boolean isRegisteredEmail(String email);

    //
//    long isRegisteredEmailAndActivated(String email);
//
    boolean isRegisteredPhone(String phone);
//
//    int activateUser(String email);
}
