package com.teamone.clivet.service;

import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.appointment.dto.AppointmentDto;
import com.teamone.clivet.model.appointment.dto.AppointmentListDto;
import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.model.user.User;
import com.teamone.clivet.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    private final PetService petService;
    private final AppointmentRepository appointmentRepository;
    private final UserService userService;

    @Override
    public AppointmentDto save(AppointmentDto dto, Long petId) {
        Pet pet = petService.findById(petId);
        dto.setPet(pet);
        Appointment appointment = appointmentRepository.save(AppointmentDto.mapToModel(dto));
        return AppointmentDto.mapToDto(appointment);
    }

    @Override
    public List <AppointmentListDto> getByPetId(Long petId) {
        Pet pet = petService.findById(petId);
        List<Appointment> appointments = appointmentRepository.findAppointmentsByPet(pet);
        return AppointmentListDto.mapToDto(appointments);
    }

    @Override
    public AppointmentDto update(Long id, AppointmentDto dto) {
        Appointment appointment = null;
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if(optionalAppointment.isPresent()){
            appointment = optionalAppointment.get();
            appointment.setDate(dto.getDate());
            appointment.setDescription(dto.getDescription());
            appointment.setMedicine(dto.getMedicine());
            appointment.setCost(dto.getCost());

            Appointment save = appointmentRepository.save(appointment);
            return AppointmentDto.mapToDto(save);
        } else {
            throw new EntityNotFoundException("Appointment with ID: " + id + " not found");
        }

    }

    @Override
    public void delete(Long appId) {
        appointmentRepository.deleteById(appId);
    }


}
