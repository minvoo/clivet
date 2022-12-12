package com.teamone.clivet.service;

import com.teamone.clivet.model.user.User;
import com.teamone.clivet.model.user.UserRole;
import com.teamone.clivet.model.user.dto.UserRegisterDto;
import com.teamone.clivet.repository.UserRepository;
import com.teamone.clivet.security.UserPrinciple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
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
        user.setRole(UserRole.USER);
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
    public Optional<Long> getLoggedInUserId(Principal principal) {
        if (principal != null){
            Authentication authentication = (Authentication) principal;
            try{
                Optional<User> user = findByUsername((String) authentication.getPrincipal());
                return Optional.of(user.get().getId());
            }catch (UsernameNotFoundException usernameNotFoundException){
                log.info("Nie jesteśmy zalogowani");
            }
        }return Optional.empty();
    }

//    @Override
//    public boolean isAdmin(Principal principal) {
//        if (principal != null){
//            Authentication authentication = (Authentication) principal;
//            try{
//                Optional<User> user =findByUsername((String) authentication.getPrincipal());
//                return UserPrinciple.getAuthorities().stream()
//                        .map(GrantedAuthority::getAuthority)
//                        .collect(Collectors.toSet())
//                        .contains("ROLE_ADMIN");
//            }catch (UsernameNotFoundException usernameNotFoundException){
//                log.info("Nie jesteśmy zalogowani");
//            }
//        }
//        return false;
//    }

}
