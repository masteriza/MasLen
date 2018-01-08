package com.maslen.services;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.models.User;
import com.maslen.services.interfaces.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInServiceImpl implements LogInService {
    private final UserDao userDao;

    @Autowired
    public LogInServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String email) {
        return userDao.getUserByEmail(email);
    }
}
