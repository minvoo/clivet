package com.teamone.clivet.controller;

import com.teamone.clivet.exception.ElementNotFoundException;
import com.teamone.clivet.exception.EmptyListException;
import com.teamone.clivet.exception.handler.UserRestExceptionHandler;
import com.teamone.clivet.model.user.User;
import com.teamone.clivet.model.user.dto.UserDetailsDto;
import com.teamone.clivet.model.user.dto.UserListDto;
import com.teamone.clivet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OwnerController {

    @Autowired
    UserService userService;
    @Autowired
    UserRestExceptionHandler exceptionHandler;

    @GetMapping("/owners")
    public ResponseEntity<?> getOwnersList() {

        List<UserListDto> allUsers = userService.getAllUsers();
        if (allUsers.isEmpty()) {
            return exceptionHandler.handleException(HttpStatus.NO_CONTENT, new EmptyListException("Owners"));
        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/owners/{ownerId}")
    public ResponseEntity<?> getOwnerById(@PathVariable("ownerId") Long id) {

        Optional<User> userOptional  =  userService.findById(id);

        if (userOptional.isEmpty())
            return exceptionHandler.handleException(HttpStatus.NOT_FOUND,
                    new ElementNotFoundException("User", "ID", id.toString()));

        User user = userOptional.get();
        UserDetailsDto owner = UserDetailsDto.mapToDto(user);
        return new ResponseEntity<>(owner,HttpStatus.OK);


    }
}
