package com.example.AEPB.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {

    @Test
    void should_park_success_when_smart_boy_park_car_given_all_parkinglot_is_empty() throws ParkingLotException {
        List<ParkingLot> parkingLots = initParkingLots(10);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket parkingTicket = smartParkingBoy.park(new Car());
        assertNotNull(parkingTicket);
    }

    @Test
    void should_park_success_when_smart_boy_park_car_given_parkinglot2_has_10_position_and_others_has_5_position() throws ParkingLotException {
        List<ParkingLot> parkingLots = initParkingLots(10);
        setParkingLotParkedCar(parkingLots.get(1),10);
        for (int i=0; i<10; i++) {
            if (i==1){
                continue;
            }
            setParkingLotParkedCar(parkingLots.get(i),45);
        }
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket parkingTicket = smartParkingBoy.park(new Car());

        assertEquals(parkingLots.get(1), parkingLots.get(parkingTicket.getParkingLotNo()));
    }

    @Test
    void should_park_success_when_smart_boy_park_car_given_parkinglot1_and_parkingLot2_has_10_position_and_others_has_5_position() throws ParkingLotException {
        List<ParkingLot> parkingLots = initParkingLots(10);
        setParkingLotParkedCar(parkingLots.get(0),10);
        setParkingLotParkedCar(parkingLots.get(1),10);
        for (int i=2; i<10; i++) {
            setParkingLotParkedCar(parkingLots.get(i),45);
        }
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket parkingTicket = smartParkingBoy.park(new Car());

        assertEquals(parkingLots.get(0), parkingLots.get(parkingTicket.getParkingLotNo()));
    }

    @Test
    void should_park_fail_and_return_err_msg_when_smart_boy_park_a_car_given_all_parking_lot_has_parked_cars_50() throws ParkingLotException {
        List<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ParkingLot parkingLot = new ParkingLot(i);
            setParkingLotParkedCar(parkingLot,50);
            parkingLots.add(parkingLot);
        }

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        assertThrows(ParkingLotException.class, () -> smartParkingBoy.park(new Car()), "车位已满");
    }

    @Test
    void should_park_fail_and_return_err_msg_when_smart_boy_park_a_car_given_car_is_null_and_parking_lot_has_position() {
        List<ParkingLot> parkingLots = initParkingLots(10);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        assertThrows(ParkingLotException.class, () -> smartParkingBoy.park(null), "没有待停的车");
    }

    @Test
    void should_pick_success_when_smart_boy_pick_car_given_smart_boy_parked_the_car() throws ParkingLotException {
        List<ParkingLot> parkingLots = initParkingLots(10);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        Car pickedCar = smartParkingBoy.pick(parkingTicket);

        assertEquals(car, pickedCar);
    }

    @Test
    void should_pick_success_when_pick_car_by_self_given_smart_boy_parked_the_car() throws ParkingLotException {
        List<ParkingLot> parkingLots = initParkingLots(10);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        Integer parkingLotNo = parkingTicket.getParkingLotNo();
        ParkingLot parkingLot = parkingLots.get(parkingLotNo);
        Car pickedCar = parkingLot.pick(parkingTicket);

        assertEquals(car, pickedCar);
    }

    @Test
    void should_pick_success_when_parking_boy_pick_car_given_smart_boy_parked_the_car() throws ParkingLotException {
        List<ParkingLot> parkingLots = initParkingLots(10);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        Integer parkingLotNo = parkingTicket.getParkingLotNo();
        ParkingLot parkingLot = parkingLots.get(parkingLotNo);
        Car pickedCar = parkingBoy.pick(parkingTicket);

        assertEquals(car, pickedCar);
    }

    @Test
    void should_pick_success_when_smart_boy_pick_car_given_parking_boy_parked_the_car() throws ParkingLotException {
        List<ParkingLot> parkingLots = initParkingLots(10);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Integer parkingLotNo = parkingTicket.getParkingLotNo();
        ParkingLot parkingLot = parkingLots.get(parkingLotNo);
        Car pickedCar = smartParkingBoy.pick(parkingTicket);

        assertEquals(car, pickedCar);
    }

    @Test
    void should_pick_fail_when_smart_boy_pick_car_given_smart_boy_parked_the_car_and_ticket_is_nul() throws ParkingLotException {
        List<ParkingLot> parkingLots = initParkingLots(10);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        smartParkingBoy.park(car);

        assertThrows(ParkingLotException.class, () -> smartParkingBoy.pick(null), "请拿停车票取车");
    }

    private List<ParkingLot> initParkingLots(int num) {
        List<ParkingLot> parkingLots = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            parkingLots.add(new ParkingLot(i));
        }
        return parkingLots;
    }

    private void setParkingLotParkedCar(ParkingLot parkingLot1, int num) throws ParkingLotException {
        for (int i = 0; i < num; i++) {
            parkingLot1.park(new Car());
        }
    }
}
