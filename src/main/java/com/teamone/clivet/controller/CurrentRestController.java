package com.teamone.clivet.controller;

import com.teamone.clivet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class CurrentRestController {
    @Autowired
    private PetService petService;


    @GetMapping("/myprofile/pets")
    public ResponseEntity<?> userListPetsLogged(){
        return new ResponseEntity<>(petService.getPetsByUserName(), HttpStatus.OK);
    }

}
