package com.example.AEPB.parkinglot;

public class PickingResult extends BaseResult<Car> {
    public PickingResult(String status, String message, Car data) {
        super(status, message, data);
    }

    public PickingResult() { }
}
