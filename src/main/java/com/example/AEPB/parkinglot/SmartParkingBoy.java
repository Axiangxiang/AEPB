package com.example.AEPB.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot getProperParkingLot() {
        return getParkingLots().stream()
                .filter(item -> item.getPositions() > 0)
                .sorted(Comparator.comparing(ParkingLot::getPositions).reversed())
                .findFirst()
                .orElse(null);
    }
}
