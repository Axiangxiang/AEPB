package com.example.AEPB.parkinglot;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;
    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots= parkingLots;
    }

    private ParkingLot getProperParkingLot() {
        return parkingLots.stream().filter(ParkingLot::hasPosition).findFirst().orElse(null);
    }

    public ParkingResult park(Car car) {
        ParkingLot properParkingLot = getProperParkingLot();
        return properParkingLot.park(car);
    }
}
