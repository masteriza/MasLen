//package com.maslen.services;
//
//import com.maslen.dao.interfaces.UserDao;
//import com.maslen.models.User;
//import com.maslen.services.interfaces.LogInService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class LogInServiceImpl implements LogInService {
//    private final UserDao userDao;
//
//    @Autowired
//    public LogInServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }
//
//    @Override
//    public Optional<User> logIn(String username) {
//        return username.isEmpty() ? Optional.empty() : userDao.searchUserByEmail(username);
//    }
//}
