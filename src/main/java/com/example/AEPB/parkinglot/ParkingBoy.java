package com.example.AEPB.parkinglot;

import java.util.List;

public class ParkingBoy implements SimpleParkingBoy{
    private List<ParkingLot> parkingLots;
    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots= parkingLots;
    }

    public ParkingLot getProperParkingLot() {
        return parkingLots.stream().filter(item -> item.getPositions() > 0).findFirst().orElse(null);
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

    public List<ParkingLot> getParkingLots() {
        return this.parkingLots;
    }
}
