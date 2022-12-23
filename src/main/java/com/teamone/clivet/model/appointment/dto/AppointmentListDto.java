package com.teamone.clivet.model.appointment.dto;

import com.teamone.clivet.model.appointment.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
public class AppointmentListDto {

    private Long id;
    private LocalDate date;
    private Long cost;

    public static Appointment mapToModel(AppointmentListDto dto) {
        return new Appointment()
                .setId(dto.getId())
                .setDate(dto.getDate())
                .setCost(dto.getCost());
    }
    public static AppointmentListDto mapToDto(Appointment dto) {
        return new AppointmentListDto()
                .setId(dto.getId())
                .setDate(dto.getDate())
                .setCost(dto.getCost());
    }

    public static List<AppointmentListDto> mapToDto(List<Appointment> appointments) {
        return appointments.stream()
                .map(AppointmentListDto::mapToDto)
                .collect(Collectors.toList());
    }
}
