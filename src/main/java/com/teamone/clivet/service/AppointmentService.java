package com.teamone.clivet.service;

import com.teamone.clivet.model.appointment.dto.AppointmentDto;
import com.teamone.clivet.model.appointment.dto.AppointmetnListDto;

public interface AppointmentService {

    AppointmentDto save(AppointmentDto dto, Long petId);

    AppointmetnListDto getByPetId(Long petId);
}
