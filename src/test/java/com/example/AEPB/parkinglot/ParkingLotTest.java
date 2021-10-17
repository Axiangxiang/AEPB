package com.example.AEPB.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ParkingLotTest {

    @Test
    void should_park_success_and_return_parking_ticket_when_park_a_car_given_parking_lot_has_parked_cars_less_than_50() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("鄂A88888");

        ParkingResult parkingResult = parkingLot.park(car);

        assertEquals("success", parkingResult.getStatus());
        assertEquals(car.getPlateNumber(), ((ParkingTicket) parkingResult.getData()).getBindedCarPlateNumber());
    }

    @Test
    void should_park_fail_and_return_err_msg_when_park_a_car_given_parking_lot_has_parked_cars_is_50() {
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 50; i++) {
            parkingLot.park(new Car("鄂B888" + i));
        }
        Car car = new Car("鄂A88888");

        ParkingResult parkingResult = parkingLot.park(car);

        assertEquals("fail", parkingResult.getStatus());
        assertEquals("车位已满", parkingResult.getMessage());
        assertNull(parkingResult.getData());
    }

    @Test
    void should_park_fail_and_return_err_msg_when_park_a_car_given_the_car_is_null_and_parking_lot_has_parked_cars_less_than_50() {
        ParkingLot parkingLot = new ParkingLot();

        Car car = null;

        ParkingResult parkingResult = parkingLot.park(car);

        assertEquals("fail", parkingResult.getStatus());
        assertEquals("没有待停的车", parkingResult.getMessage());
        assertNull(parkingResult.getData());
    }

    @Test
    void should_pick_success_when_pick_carA_given_park_ticket_of_carA_and_carA_had_parked_in_the_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        Car carA = new Car("鄂A88888");
        parkingLot.park(new Car("鄂B77777"));
        ParkingResult parkResult = parkingLot.park(carA);

        ParkingResult parkingResult = parkingLot.pick((ParkingTicket) parkResult.getData());

        assertEquals("success", parkingResult.getStatus());
        assertEquals(carA.getPlateNumber(), ((Car) parkingResult.getData()).getPlateNumber());
    }

    @Test
    void should_pick_fail_and_return_err_msg_when_pick_carA_given_a_null_parking_ticket_and_carA_had_parked_in_the_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        Car carA = new Car("鄂A88888");
        parkingLot.park(carA);

        ParkingResult parkingResult = parkingLot.pick(null);

        assertEquals("fail", parkingResult.getStatus());
        assertEquals("请拿停车票取车", parkingResult.getMessage());
        assertNull(parkingResult.getData());
    }

    @Test
    void should_pick_fail_and_return_err_msg_when_pick_carA_given_a_wrong_park_ticket_and_carA_had_parked_in_the_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        Car carA = new Car("鄂A88888");
        parkingLot.park(carA);
        ParkingTicket parkingTicket = new ParkingTicket(100);

        ParkingResult parkingResult = parkingLot.pick(parkingTicket);

        assertEquals("fail", parkingResult.getStatus());
        assertEquals("请拿停车票取车", parkingResult.getMessage());
        assertNull(parkingResult.getData());
    }

    @Test
    void should_pick_fail_and_return_err_msg_when_pick_carA_given_park_ticket_of_car_A_and_carA_had_not_parked_in_the_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        Car carA = new Car("鄂A88888");
        ParkingResult parkResult = parkingLot.park(carA);
        ParkingTicket parkingTicket = (ParkingTicket) parkResult.getData();
        parkingLot.pick(parkingTicket);

        ParkingResult parkingResult = parkingLot.pick(parkingTicket);

        assertEquals("fail", parkingResult.getStatus());
        assertEquals("没有对应的车", parkingResult.getMessage());
        assertNull(parkingResult.getData());
    }
}
