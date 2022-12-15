package com.teamone.clivet.controller;


import com.teamone.clivet.error.UserErrorResponse;
import com.teamone.clivet.exception.ElementNotFoundException;
import com.teamone.clivet.exception.handler.UserRestExceptionHandler;
import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.appointment.dto.AppointmentDto;
import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.service.AppointmentService;
import com.teamone.clivet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppointmentRestController {

    private final AppointmentService appointmentService;
    private final UserRestExceptionHandler exceptionHandler;
    private final PetService petService;

    @PostMapping("/pets/{petId}/appointments")
    public ResponseEntity<?> saveAppointment(@RequestBody AppointmentDto dto,
                                     @PathVariable("petId") Long petId) {

        Optional<Pet> pet = petService.findById(petId);
        if (pet.isEmpty()) {
            return exceptionHandler.handleException
                    (HttpStatus.NOT_FOUND, new ElementNotFoundException("Pet", "ID", petId.toString()));
        }
        return  new ResponseEntity<>(appointmentService.save(dto,petId), HttpStatus.CREATED);
    }
    @GetMapping("/pets/{petId}/appointments")
    public ResponseEntity<?> getAppointments (@PathVariable("petId") Long petId){

        Optional<Pet> pet = petService.findById(petId);
        if (pet.isEmpty()) {
            return exceptionHandler.handleException
                    (HttpStatus.NOT_FOUND, new ElementNotFoundException("Pet", "ID", petId.toString()));
        }
        return new ResponseEntity<>(appointmentService.getByPetId(petId), HttpStatus.OK);
    }

    @PutMapping("/appointments/{appId}")
    public ResponseEntity<?> updateAppointments (@PathVariable("appId") Long appId,
                                                 @RequestBody AppointmentDto dto){

        AppointmentDto appointmentDto = appointmentService.update(appId, dto);
        if (appointmentDto  == null)
            return exceptionHandler.handleException
                    (HttpStatus.NOT_FOUND, new ElementNotFoundException("Appointment", "ID", appId.toString()));
            return new ResponseEntity<>(appointmentDto, HttpStatus.OK);

    }
    @DeleteMapping("/appointments/{appId}")
    ResponseEntity<?> deleteAppointment(@PathVariable("appId") Long appId){

        Optional<Appointment> appointmentOptional = appointmentService.findById(appId);
        if (appointmentOptional.isEmpty()) {
            return exceptionHandler.handleException(
                    HttpStatus.NOT_FOUND, new ElementNotFoundException("Appointment", "ID", appId.toString()));
        }
        appointmentService.delete(appId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

