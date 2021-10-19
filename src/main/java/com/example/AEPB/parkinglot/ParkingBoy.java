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
        if (properParkingLot == null) {
            return new ParkingResult("fail", "车位已满", null);
        }
        return properParkingLot.park(car);
    }

    public PickingResult pick(ParkingTicket parkingTicket) {
        ParkingLot parkingLot = parkingLots.get(parkingTicket.getParkingLotNo());
        return parkingLot.pick(parkingTicket);
    }
}
