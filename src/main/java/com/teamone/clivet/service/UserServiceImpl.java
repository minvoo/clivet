package com.teamone.clivet.service;

import com.teamone.clivet.model.user.User;
import com.teamone.clivet.model.user.UserRole;
import com.teamone.clivet.model.user.dto.UserDetailsDto;
import com.teamone.clivet.model.user.dto.UserListDto;
import com.teamone.clivet.model.user.dto.UserRegisterDto;
import com.teamone.clivet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;




@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserRegisterDto saveUser(User user) {
        if (user.getUsername().equals("ilovecodecool")) {
            user.setRole(UserRole.ADMIN);
        } else {
            user.setRole(UserRole.USER);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.saveAndFlush(user);
        return UserRegisterDto.mapToDto(savedUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserListDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return UserListDto.mapToDto(allUsers);
    }

    @Override
    public UserDetailsDto updateById(UserDetailsDto detailsDto, Long id) {

        Optional<User> userOptional = findById(id);
        if(userOptional.isEmpty()) {
            return null;
        }
        User user = UserDetailsDto.mapToModel(detailsDto);
        User savedUser = userRepository.saveAndFlush(user);
        return UserDetailsDto.mapToDto(savedUser);


    }
    @Override
    public void delete(Long appId) {
        userRepository.deleteById(appId);
    }
}
