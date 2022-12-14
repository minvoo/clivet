package com.teamone.clivet.controller;


import com.teamone.clivet.model.appointment.dto.AppointmentDto;
import com.teamone.clivet.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppointmentRestController {

    private final AppointmentService appointmentService;


    @PostMapping("/pets/{petId}/appointments")
    public ResponseEntity<?> saveAppointment(@RequestBody AppointmentDto dto,
                                     @PathVariable("petId") Long petId) {

        return new ResponseEntity<>(appointmentService.save(dto,petId), HttpStatus.CREATED);
    }

    @GetMapping("/pets/{petId}/appointments")
    public ResponseEntity<?> getAppointments (@PathVariable("petId") Long petId){

        return new ResponseEntity<>(appointmentService.getByPetId(petId), HttpStatus.OK);
    }

    @PutMapping("/appointments/{appId}")
    public ResponseEntity<?> updateAppointments (@PathVariable("appId") Long appId,
                                                 @RequestBody AppointmentDto dto){
        return new ResponseEntity<>(appointmentService.update(appId, dto), HttpStatus.OK);
    }
    @DeleteMapping("/appointments/{appId}")
    void deleteAppointment(@PathVariable("appId") Long appId){
        appointmentService.delete(appId);
    }
}

