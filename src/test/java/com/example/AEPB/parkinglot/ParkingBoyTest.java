package com.example.AEPB.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {

    @Test
    void should_park_success_when_parking_boy_park_car_given_parkinglot_has_position() throws ParkingLotException {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            parkingLots.add(new ParkingLot(i));
        }
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        assertNotNull(parkingTicket);
    }

    @Test
    void should_park_success_when_parking_boy_park_car_given_parkinglot1_has_no_position_and_parkinglot2_has_position() throws ParkingLotException {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            parkingLots.add(new ParkingLot(i));
        }
        ParkingLot parkingLot1 = parkingLots.get(0);
        for (int i = 0; i < 50; i++) {
            parkingLot1.park(new Car());
        }
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        ParkingLot parkingLot2 = parkingLots.get(1);

        assertEquals(parkingLot2, parkingLots.get(parkingTicket.getParkingLotNo()));
    }

    @Test
    void should_park_fail_and_return_err_msg_when_park_a_car_given_all_parking_lot_has_parked_cars_50() throws ParkingLotException {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ParkingLot parkingLot = new ParkingLot(i);
            for (int j = 0; j < 50; j++) {
                parkingLot.park(new Car());
            }
            parkingLots.add(parkingLot);
        }

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        assertThrows(ParkingLotException.class, () -> parkingBoy.park(new Car()), "车位已满");
    }

    @Test
    void should_park_fail_and_return_err_msg_when_park_a_car_given_car_is_null_and_parking_lot_has_position() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ParkingLot parkingLot = new ParkingLot(i);
            parkingLots.add(parkingLot);
        }

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        assertThrows(ParkingLotException.class, () -> parkingBoy.park(null), "没有待停的车");
    }

    @Test
    void should_pick_success_when_parking_boy_pick_car_given_parking_boy_parked_the_car() throws ParkingLotException {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ParkingLot parkingLot = new ParkingLot(i);
            parkingLots.add(parkingLot);
        }

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car pickedCar = parkingBoy.pick(parkingTicket);

        assertEquals(car, pickedCar);
    }

    @Test
    void should_pick_success_when_pick_car_by_self_given_parking_boy_parked_the_car() throws ParkingLotException {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ParkingLot parkingLot = new ParkingLot(i);
            parkingLots.add(parkingLot);
        }

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Integer parkingLotNo = parkingTicket.getParkingLotNo();
        ParkingLot parkingLot = parkingLots.get(parkingLotNo);
        Car pickedCar = parkingLot.pick(parkingTicket);

        assertEquals(car, pickedCar);
    }

    @Test
    void should_pick_fail_when_parking_boy_pick_car_given_parking_boy_parked_the_car_and_ticket_is_nul() throws ParkingLotException {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ParkingLot parkingLot = new ParkingLot(i);
            parkingLots.add(parkingLot);
        }

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        parkingBoy.park(car);

        assertThrows(ParkingLotException.class, () -> parkingBoy.pick(null), "请拿停车票取车");
    }

}
