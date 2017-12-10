package com.maslen.security.repository;

import com.maslen.security.model.User;


public interface UserRepository {
    User findByUsername(String username);
}
