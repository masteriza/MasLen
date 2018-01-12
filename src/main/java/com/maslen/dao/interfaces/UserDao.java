package com.maslen.dao.interfaces;

import com.maslen.models.User;
import com.maslen.models.UserActivity;

public interface UserDao {

    User addUser(User user);

    User getUserByEmail(String email);

    boolean isRegisteredEmail(String email);

    boolean isConfirmationEmail(String email);

    boolean isRegisteredPhone(String phone);

    boolean activateUser(String email);

    boolean addUserActivity(UserActivity userActivity);
}
