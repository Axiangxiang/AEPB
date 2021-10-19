package com.example.AEPB.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingBoyTest {

    @Test
    void should_park_success_when_parking_boy_park_car_given_parkinglot_has_position() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            parkingLots.add(new ParkingLot());
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
            parkingLots.add(new ParkingLot());
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
        assertEquals(parkingLot2, parkingTicket.getParkingLot());


    }

}
