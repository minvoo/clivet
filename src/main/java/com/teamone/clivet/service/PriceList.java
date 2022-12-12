package com.teamone.clivet.service;

import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.pet.KindOfPet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class PriceList {
    public double price(Appointment appointment){
        if (appointment.getPet().getKind().equals(KindOfPet.DOG)) {
            return KindOfPet.DOG.getPrice();
        }
        return KindOfPet.CAT.getPrice();
    }
}
