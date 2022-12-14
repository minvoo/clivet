package com.teamone.clivet.controller;

import com.teamone.clivet.service.AppointmentService;
import com.teamone.clivet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class CurrentRestController {
    @Autowired
    private PetService petService;

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/myprofile/pets")
    public ResponseEntity<?> userListPetsLogged(){
        return new ResponseEntity<>(petService.getPetsByUserName(), HttpStatus.OK);
    }

    @GetMapping("/myprofile/pets/{petId}/appoitments")
    public ResponseEntity<?> petListAppoitments(@PathVariable("petId") Long petId){
        return new ResponseEntity<>(appointmentService.getByPetIdLog(petId), HttpStatus.OK);
    }


}
