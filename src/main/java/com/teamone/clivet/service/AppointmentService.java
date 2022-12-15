package com.teamone.clivet.service;

import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.appointment.dto.AppointmentDto;
import com.teamone.clivet.model.appointment.dto.AppointmentListDto;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    AppointmentDto save(AppointmentDto dto, Long petId);

    List<AppointmentListDto> getByPetId(Long petId);

    AppointmentDto update(Long id, AppointmentDto dto);

    void delete(Long appId);

    Optional<Appointment> findById(Long appId);
}
