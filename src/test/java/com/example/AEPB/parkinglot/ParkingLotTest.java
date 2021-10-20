package com.example.AEPB.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void should_park_success_and_return_parking_ticket_when_park_a_car_given_parking_lot_has_parked_cars_less_than_50() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingLot.park(car);

        assertNotNull(parkingTicket);
    }

    @Test
    void should_park_fail_and_return_err_msg_when_park_a_car_given_parking_lot_has_parked_cars_is_50() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1);
        for (int i = 0; i < 50; i++) {
            parkingLot.park(new Car());
        }
        Car car = new Car();

        assertThrows(ParkingLotException.class, () -> parkingLot.park(car), "车位已满");
    }

    @Test
    void should_park_fail_and_return_err_msg_when_park_a_car_given_the_car_is_null_and_parking_lot_has_parked_cars_less_than_50() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = null;
        assertThrows(ParkingLotException.class, () -> parkingLot.park(car), "没有待停的车");
    }

    @Test
    void should_pick_success_when_pick_carA_given_park_ticket_of_carA_and_carA_had_parked_in_the_parking_lot() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1);
        Car carA = new Car();
        parkingLot.park(new Car());
        ParkingTicket parkingTicket = parkingLot.park(carA);

        Car pickedCar = parkingLot.pick(parkingTicket);

        assertEquals(carA, pickedCar);
    }

    @Test
    void should_pick_fail_and_return_err_msg_when_pick_carA_given_a_null_parking_ticket_and_carA_had_parked_in_the_parking_lot() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1);
        Car carA = new Car();
        parkingLot.park(carA);

        ParkingTicket parkingTicket = null;

        assertThrows(ParkingLotException.class, () -> parkingLot.pick(parkingTicket), "请拿停车票取车");
    }

    @Test
    void should_pick_fail_and_return_err_msg_when_pick_carA_given_park_ticket_of_car_A_and_carA_had_not_parked_in_the_parking_lot() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1);
        Car carA = new Car();
        ParkingTicket parkingTicket = parkingLot.park(carA);
        parkingLot.pick(parkingTicket);

        assertThrows(ParkingLotException.class, () -> parkingLot.pick(parkingTicket), "没有对应的车");
    }
}
