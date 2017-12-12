package com.maslen.security.repository;

import com.maslen.models.User;

public interface UserRepository {
    User findByUsername(String username);
}
