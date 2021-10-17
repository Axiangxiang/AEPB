package com.example.AEPB.parkinglot;

public class ParkingResult {
    private String status;
    private String message;
    private Object data;

    public ParkingResult(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ParkingResult() { }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
