package com.teamone.clivet.exception.handler;

import com.sun.net.httpserver.HttpsServer;
import com.teamone.clivet.error.UserErrorResponse;
import com.teamone.clivet.exception.ElementNotFoundException;
import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.service.AppointmentService;
import com.teamone.clivet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class UserRestExceptionHandler {

    @Autowired
    PetService petService;
    @Autowired
    AppointmentService appointmentService;
   @ExceptionHandler
   public ResponseEntity<UserErrorResponse> handleException(HttpStatus httpStatus, Exception ex) {
        return new ResponseEntity<>(new UserErrorResponse(httpStatus.value(),
                ex.getMessage(),System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }



    public Optional<ResponseEntity<?>> handlePetNotFoundException(Long petId) {
        Optional<Pet> pet = petService.findById(petId);
        if (pet.isEmpty()) {
            return Optional.ofNullable(handleException
                    (HttpStatus.NOT_FOUND, new ElementNotFoundException("Pet", "ID", petId.toString())));
        }
        return Optional.empty();
    }

}
