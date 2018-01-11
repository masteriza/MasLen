package com.maslen.dao.interfaces;

import com.maslen.models.User;

import java.util.Date;

public interface UserDao {

    User addUser(User user);

    User getUserByEmail(String email);

    boolean isRegisteredEmail(String email);

    boolean isConfirmationEmail(String email);

    boolean isRegisteredPhone(String phone);

    boolean activateUser(String email);

    void addUserActivity(Date endDate, String action, String session, String status);
}
