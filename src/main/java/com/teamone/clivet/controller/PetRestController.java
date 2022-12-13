package com.teamone.clivet.controller;

import com.teamone.clivet.model.pet.dto.PetRegisterDto;
import com.teamone.clivet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class PetRestController {

    @Autowired
    private PetService petService;

    @PostMapping("/owner/{ownerId}/pets")
    public ResponseEntity<?> savePet(@RequestBody PetRegisterDto petDto,
                                     @PathVariable("ownerId") Long ownerId) {

        return new ResponseEntity<>(petService.save(petDto, ownerId), HttpStatus.CREATED);
    }

    @GetMapping("/owner/{ownerId}/pets")
    public ResponseEntity<?> listOwnersPets(@PathVariable("ownerId") Long ownerId) {

        return new ResponseEntity<>(petService.getPetsByOwnerId(ownerId), HttpStatus.CREATED);

    }
    @GetMapping("/myprofile/pets")
    public ResponseEntity<?> userListPetsLogged(){
        return new ResponseEntity<>(petService.getPetsByUserName(),HttpStatus.OK);
    }

}