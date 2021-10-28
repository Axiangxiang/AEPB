package com.example.AEPB.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.AEPB.parkinglot.TestDataUtils.buildParkingLot;
import static com.example.AEPB.parkinglot.TestDataUtils.initParkingLotsWith50Spaces;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingRobotTest {

    @Test
    void should_park_success_when_smart_boy_park_car_given_all_parkinglot_is_empty() throws ParkingLotException {
        List<ParkingLot> parkingLots = initParkingLotsWith50Spaces(10);
        ParkingRobot parkingRobot = new ParkingRobot(parkingLots);
        ParkingTicket parkingTicket = parkingRobot.park(new Car());
        assertNotNull(parkingTicket);
    }

    @Test
    void should_park_success_when_parking_robot_park_car_given_parkinglot2_has_largest_vacancy_rate_and_all_parkinglot_size_is_same() throws ParkingLotException {
        List<ParkingLot> parkingLots = initParkingLotsWith50Spaces(10);
        TestDataUtils.setParkingLotParkedCar(parkingLots.get(1), 10);
        for (int i = 0; i < 10; i++) {
            if (i == 1) {
                continue;
            }
            TestDataUtils.setParkingLotParkedCar(parkingLots.get(i), 45);
        }
        ParkingRobot parkingRobot = new ParkingRobot(parkingLots);
        ParkingTicket parkingTicket = parkingRobot.park(new Car());

        assertEquals(parkingLots.get(1), parkingLots.get(parkingTicket.getParkingLotNo()));
    }

    @Test
    void should_park_success_when_parking_robot_park_car_given_parkinglot1_and_parkingLot2_has_same_largest_vacancy_rate() throws ParkingLotException {
        List<ParkingLot> parkingLots = initParkingLotsWith50Spaces(10);
        TestDataUtils.setParkingLotParkedCar(parkingLots.get(0), 10);
        TestDataUtils.setParkingLotParkedCar(parkingLots.get(1), 10);
        for (int i = 2; i < 10; i++) {
            TestDataUtils.setParkingLotParkedCar(parkingLots.get(i), 45);
        }
        ParkingRobot parkingRobot = new ParkingRobot(parkingLots);
        ParkingTicket parkingTicket = parkingRobot.park(new Car());

        assertEquals(parkingLots.get(0), parkingLots.get(parkingTicket.getParkingLotNo()));
    }

    @Test
    void should_park_success_when_parking_robot_park_car_given_parkinglot2_has_larget_vacancy_rate_and_every_parkinglot_have_different_size() throws ParkingLotException {
        List<ParkingLot> parkingLots =new ArrayList<>( Arrays.asList(buildParkingLot(1, 10, 5), //1/2
                buildParkingLot(2, 20, 5),// 15/20
                buildParkingLot(3, 30, 15))); //1/2

        ParkingRobot parkingRobot = new ParkingRobot(parkingLots);
        ParkingTicket parkingTicket = parkingRobot.park(new Car());

        assertEquals(2, parkingTicket.getParkingLotNo());
    }

    @Test
    void should_park_fail_and_return_err_msg_when_parking_robot_park_a_car_given_all_parking_lot_have_no_space() throws ParkingLotException {
        //todo
        List<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ParkingLot parkingLot = new ParkingLot(i, 50);
            TestDataUtils.setParkingLotParkedCar(parkingLot, 50);
            parkingLots.add(parkingLot);
        }

        ParkingRobot parkingRobot = new ParkingRobot(parkingLots);

        assertThrows(ParkingLotException.class, () -> parkingRobot.park(new Car()), "车位已满");
    }

    @Test
    void should_park_fail_and_return_err_msg_when_parking_robot_park_a_car_given_car_is_null_and_parking_lot_has_position() throws ParkingLotException {
        //todo
        List<ParkingLot> parkingLots = initParkingLotsWith50Spaces(10);
        ParkingRobot parkingRobot = new ParkingRobot(parkingLots);
        assertThrows(ParkingLotException.class, () -> parkingRobot.park(null), "没有待停的车");
    }
}
