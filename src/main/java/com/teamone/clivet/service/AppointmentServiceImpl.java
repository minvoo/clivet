package com.teamone.clivet.service;

import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.appointment.dto.AppointmentDto;
import com.teamone.clivet.model.appointment.dto.AppointmetnListDto;
import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    private final PetService petService;
    private final AppointmentRepository appointmentRepository;

    @Override
    public AppointmentDto save(AppointmentDto dto, Long petId) {
        Pet pet = petService.findById(petId);
        dto.setPet(pet);
        Appointment appointment = appointmentRepository.save(AppointmentDto.mapToModel(dto));
        return AppointmentDto.mapToDto(appointment);
    }

    @Override
    public AppointmetnListDto getByPetId(Long petId) {
        Pet pet = petService.findById(petId);
        appointmentRepository.findAppointmentsByPet()
    }

}
