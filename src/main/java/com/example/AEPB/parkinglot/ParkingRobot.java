package com.example.AEPB.parkinglot;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingRobot implements SimpleParkingBoy {
    private List<ParkingLot> parkingLots;

    public ParkingRobot(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }
    public ParkingLot getProperParkingLot() {
        this.parkingLots.sort((o1, o2) -> {
            if ((float) o1.getPositions() / o1.getTotalSpaces() > (float) o2.getPositions() / o2.getTotalSpaces()) {
                return -1;
            }
            if ((float) o1.getPositions() / o1.getTotalSpaces() < (float) o2.getPositions() / o2.getTotalSpaces()) {
                return 1;
            }
            return 0;
        });
        return parkingLots.get(0);
    }

    @Override
    public ParkingTicket park(Car car) throws ParkingLotException {
        ParkingLot properParkingLot = getProperParkingLot();
        return properParkingLot.park(car);
    }
}
