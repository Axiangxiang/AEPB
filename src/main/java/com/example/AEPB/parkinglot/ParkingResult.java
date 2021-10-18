package com.example.AEPB.parkinglot;

public class ParkingResult extends BaseResult<ParkingTicket> {
    public ParkingResult(String status, String message, ParkingTicket data) {
        super(status, message, data);
    }

    public ParkingResult() { }
}
