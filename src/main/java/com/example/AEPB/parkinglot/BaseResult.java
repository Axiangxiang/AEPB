package com.example.AEPB.parkinglot;

public class BaseResult<T> {
    private String status;
    private String message;
    private T data;

    public BaseResult(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public BaseResult() { }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
