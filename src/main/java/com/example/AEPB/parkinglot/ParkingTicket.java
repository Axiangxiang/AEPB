package com.example.AEPB.parkinglot;

public class ParkingTicket {
    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    private ParkingLot parkingLot;

    public ParkingTicket(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
