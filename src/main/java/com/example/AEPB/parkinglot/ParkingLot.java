package com.example.AEPB.parkinglot;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLot {
    private final Integer TOTAL_PARKING_SPACES = 50;
    private List<ParkingTicket> parkingTickets = new ArrayList<>();
    private List<Car> parkedCars = new ArrayList<>();

    public ParkingLot() {
        initParkingTicket();
    }

    public ParkingResult park(Car car) {
        ParkingResult parkingResult = new ParkingResult();
        if (!doParkCheck(parkingResult, car)) {
            return parkingResult;
        }
        List<ParkingTicket> availableParkingTickets = parkingTickets.stream()
                .filter(item -> item.getBindedCarPlateNumber() == null)
                .collect(Collectors.toList());
        parkedCars.add(car);
        availableParkingTickets.get(0).bindCar(car);
        parkingResult.setStatus("success");
        parkingResult.setMessage("停车成功");
        parkingResult.setData(availableParkingTickets.get(0));
        return parkingResult;
    }

    public PickingResult pick(ParkingTicket parkingTicket) {
        PickingResult pickingResult = new PickingResult();
        if (!doPickCheck(pickingResult, parkingTicket)) {
            return pickingResult;
        }
        List<Car> parkedCarList = this.parkedCars.stream()
                .filter(item -> item.getPlateNumber().equals(parkingTicket.getBindedCarPlateNumber()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(parkedCarList)) {
            pickingResult.setStatus("fail");
            pickingResult.setMessage("没有对应的车");
            return pickingResult;
        }
        parkedCars.remove(parkedCarList.get(0));
        recycleParkingTickets(parkingTicket);
        pickingResult.setStatus("success");
        pickingResult.setMessage("取车成功");
        pickingResult.setData(parkedCarList.get(0));
        return pickingResult;
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
        List<ParkingTicket> correctParkingTickets = this.parkingTickets.stream()
                .filter(item -> item.getUniqueNo() == parkingTicket.getUniqueNo())
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(correctParkingTickets)) {
            pickingResult.setStatus("fail");
            pickingResult.setMessage("请拿停车票取车");
            return false;
        }
        return true;
    }

    private void recycleParkingTickets(ParkingTicket parkingTicket) {
        parkingTickets.forEach(item -> {
            if (item.getUniqueNo() == parkingTicket.getUniqueNo()) {
                parkingTicket.unbindCar();
            }
        });
    }

    private void initParkingTicket() {
        for (int i = 0; i < 50; i++) {
            parkingTickets.add(new ParkingTicket(i));
        }
    }
}
