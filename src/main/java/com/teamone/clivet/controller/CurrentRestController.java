package com.teamone.clivet.controller;

import com.teamone.clivet.exception.ElementNotFoundException;
import com.teamone.clivet.exception.handler.UserRestExceptionHandler;
import com.teamone.clivet.model.appointment.dto.AppointmentListDto;
import com.teamone.clivet.service.AppointmentService;
import com.teamone.clivet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class CurrentRestController {
    @Autowired
    private PetService petService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserRestExceptionHandler userRestExceptionHandler;

    @GetMapping("/myprofile/pets")
    public ResponseEntity<?> userListPetsLogged(){
        return new ResponseEntity<>(petService.getPetsByUserName(), HttpStatus.OK);
    }

    @GetMapping("/myprofile/pets/{petId}/appointments")
    public ResponseEntity<?> petListAppoitments(@PathVariable("petId") Long petId){
        List<AppointmentListDto> byPetIdLog = appointmentService.getByPetIdLog(petId);
        if (byPetIdLog==null){
            return userRestExceptionHandler.handleException(HttpStatus.NOT_FOUND,
                    new ElementNotFoundException("Pet"," ID",petId.toString()));
        }
        return new ResponseEntity<>(appointmentService.getByPetIdLog(petId), HttpStatus.OK);
    }


}
