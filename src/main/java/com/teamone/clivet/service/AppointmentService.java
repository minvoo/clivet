package com.teamone.clivet.service;

import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.appointment.dto.AppointmentDetailsDto;
import com.teamone.clivet.model.appointment.dto.AppointmentDto;
import com.teamone.clivet.model.pet.dto.PetRegisterDto;

import java.util.List;

public interface AppointmentService {
    Long addAppointment(AppointmentDto appointment, String username);
    void addPetToAppointment(Long appointmentId, PetRegisterDto pet);
    void addUserToAppointment(Long userId, Long appointmentID);
    AppointmentDetailsDto findAppointment(Long appointmentId);
    void cancel(Long appointmentId);
    List<AppointmentDto> findUserAppointment(Long userId);
    List<AppointmentDto> findAll();

}
