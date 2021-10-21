package com.example.AEPB.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartBoy {
    private List<ParkingLot> parkingLots;
    public SmartBoy(List<ParkingLot> parkingLots) {
        this.parkingLots= parkingLots;
    }

    private ParkingLot getProperParkingLot() {
        return parkingLots.stream()
                .filter(item -> item.getPositions() > 0)
                .sorted(Comparator.comparing(ParkingLot::getPositions).reversed())
                .findFirst()
                .orElse(null);
    }

    public ParkingTicket park(Car car) throws ParkingLotException {
        ParkingLot properParkingLot = getProperParkingLot();
        if (properParkingLot == null) {
            throw new ParkingLotException("车位已满");
        }
        return properParkingLot.park(car);
    }

    public Car pick(ParkingTicket parkingTicket) throws ParkingLotException {
        if (parkingTicket == null) {
            throw new ParkingLotException("请拿停车票取车");
        }
        ParkingLot parkingLot = parkingLots.get(parkingTicket.getParkingLotNo());
        return parkingLot.pick(parkingTicket);
    }
}
