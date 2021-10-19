package com.example.AEPB.parkinglot;

public class ParkingTicket {
    public Integer getParkingLotNo() {
        return parkingLotNo;
    }

    private Integer parkingLotNo;

    public ParkingTicket(Integer parkingLotNo) {
        this.parkingLotNo = parkingLotNo;
    }
}
