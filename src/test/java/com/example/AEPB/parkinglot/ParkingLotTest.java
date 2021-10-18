package com.example.AEPB.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void should_park_success_and_return_parking_ticket_when_park_a_car_given_parking_lot_has_parked_cars_less_than_50() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        ParkingResult parkingResult = parkingLot.park(car);

        assertEquals("success", parkingResult.getStatus());
        assertNotNull(parkingResult.getData());
    }

    @Test
    void should_park_fail_and_return_err_msg_when_park_a_car_given_parking_lot_has_parked_cars_is_50() {
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 50; i++) {
            parkingLot.park(new Car());
        }
        Car car = new Car();

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
        Car carA = new Car();
        parkingLot.park(new Car());
        ParkingResult parkResult = parkingLot.park(carA);

        PickingResult pickingResult = parkingLot.pick(parkResult.getData());

        assertEquals("success", pickingResult.getStatus());
        assertEquals(carA, pickingResult.getData());
    }

    @Test
    void should_pick_fail_and_return_err_msg_when_pick_carA_given_a_null_parking_ticket_and_carA_had_parked_in_the_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        Car carA = new Car();
        parkingLot.park(carA);

        PickingResult pickingResult = parkingLot.pick(null);

        assertEquals("fail", pickingResult.getStatus());
        assertEquals("请拿停车票取车", pickingResult.getMessage());
        assertNull(pickingResult.getData());
    }

    @Test
    void should_pick_fail_and_return_err_msg_when_pick_carA_given_park_ticket_of_car_A_and_carA_had_not_parked_in_the_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        Car carA = new Car();
        ParkingResult parkResult = parkingLot.park(carA);
        ParkingTicket parkingTicket = parkResult.getData();
        parkingLot.pick(parkingTicket);

        PickingResult pickingResult = parkingLot.pick(parkingTicket);

        assertEquals("fail", pickingResult.getStatus());
        assertEquals("没有对应的车", pickingResult.getMessage());
        assertNull(pickingResult.getData());
    }
}
