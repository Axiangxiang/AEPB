package com.example.AEPB.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Integer parkingLotNo;
    private  Integer totalParkingSpace;
    private Map<ParkingTicket, Car> parkedCars = new HashMap<>();

    public ParkingLot(Integer parkingLotNo ,Integer totalParkingSpace) throws ParkingLotException {
        if (totalParkingSpace<0 || totalParkingSpace>100000) {
            throw new ParkingLotException("停车场构建失败");
        }
        this.parkingLotNo = parkingLotNo;
        this.totalParkingSpace = totalParkingSpace;
    }




    public ParkingTicket park(Car car) throws ParkingLotException {
        if (parkedCars.size() == totalParkingSpace) {
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
        return totalParkingSpace - this.parkedCars.size();
    }

    public int getTotalSpaces() {
        return this.totalParkingSpace;
    }

}
