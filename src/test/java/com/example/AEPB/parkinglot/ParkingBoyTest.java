package com.example.AEPB.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {

    @Test
    void should_park_success_when_parking_boy_park_car_given_parkinglot_has_position() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            parkingLots.add(new ParkingLot(i));
        }
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingResult parkingResult = parkingBoy.park(new Car());
        assertEquals("success", parkingResult.getStatus());
        assertNotNull(parkingResult.getData());
    }

    @Test
    void should_park_success_when_parking_boy_park_car_given_parkinglot1_has_no_position_and_parkinglot2_has_position() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            parkingLots.add(new ParkingLot(i));
        }
        ParkingLot parkingLot1 = parkingLots.get(0);
        for (int i = 0; i < 50; i++) {
            parkingLot1.park(new Car());
        }
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingResult parkingResult = parkingBoy.park(new Car());
        ParkingLot parkingLot2 = parkingLots.get(1);
        ParkingTicket parkingTicket = parkingResult.getData();

        assertEquals("success", parkingResult.getStatus());
        assertEquals(parkingLot2, parkingLots.get(parkingTicket.getParkingLotNo()));
    }

    @Test
    void should_park_fail_and_return_err_msg_when_park_a_car_given_all_parking_lot_has_parked_cars_50() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ParkingLot parkingLot = new ParkingLot(i);
            for (int j = 0; j < 50; j++) {
                parkingLot.park(new Car());
            }
            parkingLots.add(parkingLot);
        }

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingResult parkingResult = parkingBoy.park(new Car());

        assertEquals("fail", parkingResult.getStatus());
        assertEquals("车位已满", parkingResult.getMessage());
        assertNull(parkingResult.getData());
    }

    @Test
    void should_park_fail_and_return_err_msg_when_park_a_car_given_car_is_null_and_parking_lot_has_position() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ParkingLot parkingLot = new ParkingLot(i);
            parkingLots.add(parkingLot);
        }

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingResult parkingResult = parkingBoy.park(null);

        assertEquals("fail", parkingResult.getStatus());
        assertEquals("没有待停的车", parkingResult.getMessage());
        assertNull(parkingResult.getData());
    }

    @Test
    void should_pick_success_when_parking_boy_pick_car_given_parking_boy_parked_the_car(){
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ParkingLot parkingLot = new ParkingLot(i);
            parkingLots.add(parkingLot);
        }

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        ParkingResult parkingResult = parkingBoy.park(car);
        PickingResult pickingResult = parkingBoy.pick(parkingResult.getData());

        assertEquals("success", pickingResult.getStatus());
        assertEquals(car, pickingResult.getData());

    }



}
