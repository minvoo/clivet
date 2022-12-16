package com.teamone.clivet.controller;

import com.teamone.clivet.exception.EmptyListException;
import com.teamone.clivet.exception.handler.UserRestExceptionHandler;
import com.teamone.clivet.model.user.dto.UserListDto;
import com.teamone.clivet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
