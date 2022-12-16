package com.teamone.clivet.service;

import com.teamone.clivet.model.user.User;
import com.teamone.clivet.model.user.dto.UserDetailsDto;
import com.teamone.clivet.model.user.dto.UserListDto;
import com.teamone.clivet.model.user.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    UserRegisterDto saveUser(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long ownerId);

    List<UserListDto> getAllUsers();

    UserDetailsDto updateById(UserDetailsDto detailsDto, Long id);

    ResponseEntity<?> deleteById(Long id);
}
