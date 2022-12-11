package com.teamone.clivet.service;

import com.teamone.clivet.model.user.User;
import com.teamone.clivet.model.user.dto.UserRegisterDto;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    UserRegisterDto saveUser(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long ownerId);
}
