package com.example.AEPB.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtils {
    public static List<ParkingLot> initParkingLotsWith50Spaces(int num) throws ParkingLotException {
        List<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            parkingLots.add(new ParkingLot(i,50));
        }
        return parkingLots;
    }

    public static void setParkingLotParkedCar(ParkingLot parkingLot1, int num) throws ParkingLotException {
        for (int i = 0; i < num; i++) {
            parkingLot1.park(new Car());
        }
    }

    public static ParkingLot buildParkingLot(Integer parkingLotNo, Integer totalSpaces, Integer parkedCars) throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(parkingLotNo, totalSpaces);
        setParkingLotParkedCar(parkingLot, parkedCars);
        return parkingLot;
    }

}
