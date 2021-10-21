package com.example.AEPB.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Integer parkingLotNo;
    private final Integer TOTAL_PARKING_SPACES = 50;
    private Map<ParkingTicket, Car> parkedCars = new HashMap<>();

    public ParkingLot(Integer parkingLotNo) {
        this.parkingLotNo = parkingLotNo;
    }

    public ParkingTicket park(Car car) throws ParkingLotException {
        if (parkedCars.size() == TOTAL_PARKING_SPACES) {
            throw new ParkingLotException("车位已满");
        }
        if (car == null) {
            throw new ParkingLotException("没有待停的车");
        }
        ParkingTicket parkingTicket = new ParkingTicket(this.parkingLotNo);
        parkedCars.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car pick(ParkingTicket parkingTicket) throws ParkingLotException {
        if (parkingTicket == null) {
            throw new ParkingLotException("请拿停车票取车");
        }
        Car car = parkedCars.get(parkingTicket);
        if (car == null) {
            throw new ParkingLotException("没有对应的车");
        }
        parkedCars.remove(parkingTicket);
        return car;
    }

    public int getPositions() {
        return TOTAL_PARKING_SPACES - this.parkedCars.size();
    }
}
