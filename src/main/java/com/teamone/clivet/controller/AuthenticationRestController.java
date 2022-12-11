package com.teamone.clivet.controller;

import com.teamone.clivet.exception.BadRequestException;
import com.teamone.clivet.exception.ElementAlreadyExistsException;
import com.teamone.clivet.exception.handler.UserRestExceptionHandler;
import com.teamone.clivet.model.user.User;
import com.teamone.clivet.model.user.dto.UserLoginDto;
import com.teamone.clivet.service.AuthenticationService;
import com.teamone.clivet.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api")
@Slf4j
public class AuthenticationRestController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRestExceptionHandler exceptionHandler;

    @PostMapping("/register") //api/register
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userService.findByUsername(user.getUsername()).isPresent()
                || userService.findByEmail(user.getEmail()).isPresent()) {
            return exceptionHandler.handleException(HttpStatus.CONFLICT, new ElementAlreadyExistsException("User"));
        }
        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null
                || user.getFirstName() == null || user.getLastName() == null) {
            return exceptionHandler.handleException(HttpStatus.BAD_REQUEST, new BadRequestException());

        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login") //api/login
    public ResponseEntity<?> login(@RequestBody UserLoginDto userDto) {

        Optional<User> userOptional = userService.findByUsername(userDto.getUsername());

        if (!userOptional.isPresent()) {
            return exceptionHandler.handleException(HttpStatus.BAD_REQUEST, new BadCredentialsException("Bad credentials provided"));
        }
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(UserLoginDto.mapToModel(userDto)), HttpStatus.OK);

    }


}
