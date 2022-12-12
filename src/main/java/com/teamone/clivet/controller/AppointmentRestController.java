package com.teamone.clivet.controller;


import com.teamone.clivet.model.appointment.dto.AppointmentDto;
import com.teamone.clivet.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class AppointmentRestController {

    private final AppointmentService appointmentService;


    @PostMapping("/{petId}/appointments")
    public ResponseEntity<?> saveAppointment(@RequestBody AppointmentDto dto,
                                     @PathVariable("petId") Long petId) {

        return new ResponseEntity<>(appointmentService.save(dto,petId), HttpStatus.CREATED);
    }

    @GetMapping("/{petId}/appointments")
    public ResponseEntity<?> getAppointments (@PathVariable("petId") Long petId){

        return new ResponseEntity(appointmentService.getByPetId(petId));
    }

}

