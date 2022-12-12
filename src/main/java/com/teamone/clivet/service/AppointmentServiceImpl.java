package com.teamone.clivet.service;

import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.appointment.dto.AppointmentDetailsDto;
import com.teamone.clivet.model.appointment.dto.AppointmentDto;
import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.model.pet.dto.PetRegisterDto;
import com.teamone.clivet.model.user.User;
import com.teamone.clivet.repository.AppointmentRepository;
import com.teamone.clivet.repository.PetRepository;
import com.teamone.clivet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService{

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final AppointmentRepository appointmentRepository;
    private final PriceList priceList;
    private final UserService userService;


    @Override
    public Long addAppointment(AppointmentDto appointment, String username) {
        User user = null;

        Optional<User> userOptional = userService.findByUsername(username);
        if(userOptional.isPresent()){
            user = userOptional.get();
        } else {
            throw new EntityNotFoundException("User with username: " + username + " not found");
        }
        Appointment createdAppointment = Appointment.builder()
                .date(appointment.getDate())
                .description(appointment.getDescription())
                .user(user)
                .build();
        return appointmentRepository.save(createdAppointment).getId();
    }

    @Override
    public void addPetToAppointment(Long appointmentId, PetRegisterDto pet) {
        Appointment appointment = null;
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if(appointmentOptional.isPresent()){
            appointment = appointmentOptional.get();
        }else {
            throw new EntityNotFoundException("Appointment with ID: " + appointmentId + " not found");
        }

        Pet createdPet = Pet.builder()
                .appointment(appointment)
                .name(pet.getName())
                .kind(pet.getKind())
                .weight(pet.getWeight())
                .age(pet.getAge())
                .build();
        appointment.setPet(createdPet);
        petRepository.save(createdPet);


        appointment.setCost(priceList.price(appointment));
        appointmentRepository.save(appointment);
    }

    @Override
    public void addUserToAppointment(Long userId, Long appointmentID) {
        User user = null;
        Appointment appointment = null;

        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            user = userOptional.get();
        } else {
            throw new EntityNotFoundException("User with ID: " + userId + " not found");
        }
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentID);
        if(appointmentOptional.isPresent()){
            appointment = appointmentOptional.get();
        } else{
            throw new EntityNotFoundException("Appointment with ID: " + appointmentID + " not found");
        }
        appointment.setUser(user);
        appointmentRepository.save(appointment);
    }

    @Override
    public AppointmentDetailsDto findAppointment(Long appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            return AppointmentDetailsDto.builder()
                    .id(appointment.getId())
                    .date(appointment.getDate())
                    .description(appointment.getDescription())
                    .medicine(appointment.getMedicine())
                    .cost(appointment.getCost())
                    .pet(appointment.getPet())
                    .kind(appointment.getKind())
                    .cancelled(appointment.isCancelled())
                    .build();
        }
        throw new EntityNotFoundException("Appointment with ID: " + appointmentId + " not found");
    }

    @Override
    public void cancel(Long appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if(optionalAppointment.isPresent()){
            Appointment appointment = optionalAppointment.get();
            appointment.setCancelled(true);
            appointmentRepository.save(appointment);
        }
    }

    @Override
    public List<AppointmentDto> findUserAppointment(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return appointmentRepository.findByUser(user).stream()
                    .map(appointment -> AppointmentDto.builder()
                            .date(appointment.getDate())
                            .cost(appointment.getCost())
                            .pet(appointment.getPet())
                            .description(appointment.getDescription())
                            .medicine(appointment.getMedicine())
                            .build()).collect(Collectors.toList());
        }
        throw new EntityNotFoundException("User with ID: " + userId + " not found");
    }

    @Override
    public List<AppointmentDto> findAll() {
        return appointmentRepository.findAll().stream()
                .map(appointment -> AppointmentDto.builder()
                        .date(appointment.getDate())
                        .cost(appointment.getCost())
                        .pet(appointment.getPet())
//                        .description(appointment.getDescription())  Takie dane mozna by bylo wyswietlac w jakis szczegolach wizyty,
//                        .medicine(appointment.getMedicine())        na froncie bylby to jakis przycisk przy wizycie czy cos
                        .build())
                .collect(Collectors.toList());
    }
}
