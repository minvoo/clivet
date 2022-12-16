package com.teamone.clivet.controller;

import com.teamone.clivet.delete.SuccessDelete;
import com.teamone.clivet.delete.SuccessDeleteHandler;
import com.teamone.clivet.exception.ElementNotFoundException;
import com.teamone.clivet.exception.EmptyListException;
import com.teamone.clivet.exception.handler.UserRestExceptionHandler;
import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.user.User;
import com.teamone.clivet.model.user.dto.UserDetailsDto;
import com.teamone.clivet.model.user.dto.UserListDto;
import com.teamone.clivet.repository.UserRepository;
import com.teamone.clivet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OwnerRestController {

    @Autowired
    UserService userService;
    @Autowired
    UserRestExceptionHandler exceptionHandler;

    @Autowired
    SuccessDeleteHandler deleteHandler;

    @Autowired
    UserRepository userRepository;

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

        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isEmpty())
            return exceptionHandler.handleException(HttpStatus.NOT_FOUND,
                    new ElementNotFoundException("User", "ID", id.toString()));

        User user = userOptional.get();
        UserDetailsDto owner = UserDetailsDto.mapToDto(user);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @PatchMapping("/owners/{ownerId}")
    public ResponseEntity<?> updateOwnerById(@PathVariable("ownerId") Long id,
                                             @RequestBody UserDetailsDto detailsDto) {

        UserDetailsDto userDetailsDto = userService.updateById(detailsDto, id);

        if (userDetailsDto == null) {
            return exceptionHandler.handleException(HttpStatus.NOT_FOUND,
                    new ElementNotFoundException("User", "ID", id.toString()));
        }
        return new ResponseEntity<>(userDetailsDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/owners/{ownerId}")
    public ResponseEntity<?> deleteOwnerById(@PathVariable("ownerId") Long id) {

        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isEmpty()) {
            return exceptionHandler.handleException(
                    HttpStatus.NOT_FOUND, new ElementNotFoundException("User", "ID", id.toString()));
        }
        userService.delete(id);

        return deleteHandler.handleDelete(HttpStatus.OK, new SuccessDelete());
    }
}
