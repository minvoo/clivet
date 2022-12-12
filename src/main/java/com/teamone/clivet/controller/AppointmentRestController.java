package com.teamone.clivet.controller;

import com.teamone.clivet.exception.UnauthorizedUserException;
import com.teamone.clivet.model.appointment.dto.AppointmentDto;
import com.teamone.clivet.model.user.User;
import com.teamone.clivet.repository.UserRepository;
import com.teamone.clivet.service.AppointmentService;
import com.teamone.clivet.service.AuthenticationService;
import com.teamone.clivet.service.PriceList;
import com.teamone.clivet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RequestMapping("/api/appointments")
@RestController
@RequiredArgsConstructor
public class AppointmentRestController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final AppointmentService appointmentService;


    @PostMapping("")
    public Long add(@RequestBody AppointmentDto appointment, String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        if(userOptional.isPresent() && !authenticationService.isAdmin()){
           return appointmentService.addAppointment(appointment, username);
        }
        throw new UnauthorizedUserException("User " + username + " is not authorized");
    }
}
