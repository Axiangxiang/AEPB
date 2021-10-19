package com.example.AEPB.parkinglot;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {
    private final Integer TOTAL_PARKING_SPACES = 50;
    private List<ParkingTicket> parkingTickets = new ArrayList<>();
    private Map<ParkingTicket, Car> parkedCars = new HashMap<>();

    public ParkingResult park(Car car) {
        ParkingResult parkingResult = new ParkingResult();
        if (!doParkCheck(parkingResult, car)) {
            return parkingResult;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkedCars.put(parkingTicket, car);
        parkingResult.setStatus("success");
        parkingResult.setMessage("停车成功");
        parkingResult.setData(parkingTicket);
        return parkingResult;
    }

    public PickingResult pick(ParkingTicket parkingTicket) {
        PickingResult pickingResult = new PickingResult();
        if (!doPickCheck(pickingResult, parkingTicket)) {
            return pickingResult;
        }
        Car car = parkedCars.get(parkingTicket);
        if (car == null) {
            pickingResult.setStatus("fail");
            pickingResult.setMessage("没有对应的车");
            return pickingResult;
        }
        parkedCars.remove(parkingTicket);
        pickingResult.setStatus("success");
        pickingResult.setMessage("取车成功");
        pickingResult.setData(car);
        return pickingResult;
    }

    public boolean hasPosition() {
        return this.parkedCars.size() < TOTAL_PARKING_SPACES;
    }

    private boolean doParkCheck(ParkingResult parkingResult, Car car) {
        if (parkedCars.size() == TOTAL_PARKING_SPACES) {
            parkingResult.setStatus("fail");
            parkingResult.setMessage("车位已满");
            return false;
        }
        if (car == null) {
            parkingResult.setStatus("fail");
            parkingResult.setMessage("没有待停的车");
            return false;
        }
        return true;
    }

    private boolean doPickCheck(PickingResult pickingResult, ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            pickingResult.setStatus("fail");
            pickingResult.setMessage("请拿停车票取车");
            return false;
        }
        return true;
    }
}
