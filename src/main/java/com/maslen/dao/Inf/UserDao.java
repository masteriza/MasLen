package com.maslen.dao.Inf;

import com.maslen.beans.User;

public interface UserDao {
    User addUser(User user);

    User findUserById(int userId);
}
