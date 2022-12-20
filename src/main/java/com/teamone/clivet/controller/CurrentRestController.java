package com.teamone.clivet.controller;

import com.teamone.clivet.exception.ElementNotFoundException;
import com.teamone.clivet.exception.handler.UserRestExceptionHandler;
import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.appointment.dto.AppointmentDto;
import com.teamone.clivet.model.appointment.dto.AppointmentListDto;
import com.teamone.clivet.model.pet.dto.PetRegisterDto;
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
import java.util.Optional;

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

    @GetMapping("/myprofile/pets/{petId}")
    public ResponseEntity<?> getPetLogged(@PathVariable("petId") Long petId){
        PetRegisterDto pet = petService.getPetLogged(petId);

        if (pet == null) {
            return userRestExceptionHandler.handleException
                    (HttpStatus.NOT_FOUND, new ElementNotFoundException("Pet", "ID", petId.toString()));
        }

        return new ResponseEntity<>(petService.getPetLogged(petId), HttpStatus.CREATED);
    }


    @GetMapping("/myprofile/appointments/{appId}")
    public ResponseEntity<?> getAppointmentCurrentUser(Long petId,@PathVariable("appId")Long appId){
        Optional<Appointment> appointmentOptional = appointmentService.findById(appId);
        Appointment appointment = appointmentOptional.get();
        AppointmentDto appointmentDto = AppointmentDto.mapToDto(appointment);

        if (appointmentDto==null)
            return userRestExceptionHandler.handleException
                    (HttpStatus.NOT_FOUND, new ElementNotFoundException("Appointment", "ID", appId.toString()));
        return new ResponseEntity<>(appointmentService.getAppointmentByPetId(petId,appId),HttpStatus.OK);
    }




}
