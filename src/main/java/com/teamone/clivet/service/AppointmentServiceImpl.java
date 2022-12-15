package com.teamone.clivet.service;

import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.appointment.dto.AppointmentDto;
import com.teamone.clivet.model.appointment.dto.AppointmentListDto;
import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.model.pet.dto.PetRegisterDto;
import com.teamone.clivet.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final PetService petService;
    private final AppointmentRepository appointmentRepository;


    @Override
    public AppointmentDto save(AppointmentDto dto, Long petId) {
        Optional<Pet> pet = petService.findById(petId);
        if (pet.isEmpty()) {
            return null;
        }
        dto.setPet(pet.get());
        Appointment appointment = appointmentRepository.save(AppointmentDto.mapToModel(dto));
        return AppointmentDto.mapToDto(appointment);
    }

    @Override
    public List<AppointmentListDto> getByPetId(Long petId) {
        Optional<Pet> pet = petService.findById(petId);
        if (pet == null) {
            return null;
        }
        List<Appointment> appointments = appointmentRepository.findAppointmentsByPet(pet.get());
        return AppointmentListDto.mapToDto(appointments);
    }

    @Override

    public List<AppointmentListDto> getByPetIdLog(Long petId) {
        List<PetRegisterDto> petsByUserName = petService.getPetsByUserName();
        PetRegisterDto petLog = petsByUserName.stream()
                .filter(pet -> pet.getId().equals(petId))
                .findFirst().orElse(null);
        if (petLog==null){
            return null;
        }
        Pet pet = PetRegisterDto.mapToModel(petLog);
        List<Appointment> appointments = appointmentRepository.findAppointmentsByPet(pet);
        return AppointmentListDto.mapToDto(appointments);
    }

    public AppointmentDto update(Long id, AppointmentDto dto) {
        Appointment appointment = null;
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            appointment = optionalAppointment.get();
            appointment.setDate(dto.getDate());
            appointment.setDescription(dto.getDescription());
            appointment.setMedicine(dto.getMedicine());
            appointment.setCost(dto.getCost());

            Appointment save = appointmentRepository.save(appointment);
            return AppointmentDto.mapToDto(save);
        } else {
            return null;
        }

    }

    @Override
    public void delete(Long appId) {
        appointmentRepository.deleteById(appId);
    }

    @Override
    public Optional<Appointment> findById(Long appId) {

        return appointmentRepository.findById(appId);
    }



}
