package com.example.AEPB.parkinglot;

public class ParkingTicket {
    private int uniqueNo;
    private String bindedCarPlateNumber;

    public ParkingTicket(int uniqueNo) {
        this.uniqueNo = uniqueNo;
    }

    public void bindCar(Car car) {
        this.bindedCarPlateNumber = car.getPlateNumber();
    }

    public void unbindCar() {
        this.bindedCarPlateNumber = null;
    }

    public int getUniqueNo() {
        return uniqueNo;
    }

    public String getBindedCarPlateNumber() {
        return bindedCarPlateNumber;
    }
}
