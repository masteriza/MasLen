package com.maslen.dao.Inf;

import com.maslen.beans.User;

public interface UserDao {
    int addUser();

    User findUserById(int userId);
}
