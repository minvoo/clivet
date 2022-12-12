package com.teamone.clivet.model.pet;

public enum KindOfPet {
    CAT(100),
    DOG(150);

    private final double costForAppointment;


    KindOfPet(double costForAppointment) {
        this.costForAppointment = costForAppointment;
    }

    public double getPrice(){
        return costForAppointment;
    }
}
