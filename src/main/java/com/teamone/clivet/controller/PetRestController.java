package com.teamone.clivet.controller;



import com.teamone.clivet.exception.ElementNotFoundException;
import com.teamone.clivet.exception.handler.UserRestExceptionHandler;
import com.teamone.clivet.model.appointment.dto.AppointmentDto;

import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.model.pet.dto.PetRegisterDto;
import com.teamone.clivet.model.pet.dto.PetUpdateDto;
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

    @Autowired
    private UserRestExceptionHandler exceptionHandler;

    @PostMapping("/owner/{ownerId}/pets")
    public ResponseEntity<?> savePet(@RequestBody PetRegisterDto petDto,
                                     @PathVariable("ownerId") Long ownerId) {

        return new ResponseEntity<>(petService.save(petDto, ownerId), HttpStatus.CREATED);
    }

    @GetMapping("/owner/{ownerId}/pets")
    public ResponseEntity<?> listOwnersPets(@PathVariable("ownerId") Long ownerId) {

        return new ResponseEntity<>(petService.getPetsByOwnerId(ownerId), HttpStatus.CREATED);

    }



    @PatchMapping("/owner/{ownerId}/pets/{petId}")
    public ResponseEntity<?> updatePet (@PathVariable("ownerId") Long ownerId, @PathVariable("petId") Long petId,
                                                 @RequestBody PetUpdateDto dto){
        return new ResponseEntity<>(petService.updatePet(dto, ownerId, petId), HttpStatus.OK);
    }

    @DeleteMapping("/owner/{ownerId}/pets/{petId}")
    public ResponseEntity<?> deletePet(@PathVariable("ownerId") Long ownerId, @PathVariable("petId") Long petId) {
        Pet pet = petService.findPetByOwnerId(ownerId, petId);
        if (pet == null) {
            return exceptionHandler.handleException
                    (HttpStatus.NOT_FOUND, new ElementNotFoundException("Pet", "ID", petId.toString()));
        }
        petService.deletePet(pet);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}