import org.example.*;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ParkingAttendantTest {
    @Test
    public void TestPark4CarThroughParkingLotsWhenThere_is_4SlotsAvailable() throws Exception {
        ParkingAttendant parkingAttendant = new ParkingAttendant();
        ParkingLot parking = new ParkingLot(1);
        parkingAttendant.add(parking);
        ParkingLot parking1 = new ParkingLot(3);
        parkingAttendant.add(parking1);
        parkingAttendant.parkCar(new Car("XYZ4564","blue"));
        parkingAttendant.parkCar(new Car("XYZ4565","blue"));
        parkingAttendant.parkCar(new Car("XYZ4566","blue"));
        assertDoesNotThrow(() -> parkingAttendant.parkCar(new Car("XYZ4567","blue")));
    }

    @Test
    public void TestPark4CarThroughParkingLotsWhenThere_is_3SlotsAvailable() throws Exception {
        ParkingAttendant parkingAttendant = new ParkingAttendant();
        ParkingLot parking = new ParkingLot(1);
        parkingAttendant.add(parking);
        ParkingLot parking1 = new ParkingLot(2);
        parkingAttendant.add(parking1);
        parkingAttendant.parkCar(new Car("XYZ4564","blue"));
        parkingAttendant.parkCar(new Car("XYZ4565","blue"));
        parkingAttendant.parkCar(new Car("XYZ4567","blue"));
        assertThrows(Exception.class,() -> parkingAttendant.parkCar(new Car("XYZ4568","blue")));
    }

    @Test
    public void TestUnParkCarThroughParkingLotsWithValidCarTicket() throws Exception {
        ParkingAttendant parkingAttendant = new ParkingAttendant();
        ParkingLot parking = new ParkingLot(1);
        parkingAttendant.add(parking);
        Car car = new Car("xyz01","blue");
        String ticket = parking.parkCar(car);
        assertDoesNotThrow(() -> parkingAttendant.unPark(ticket));
    }

    @Test
    public void TestUnParkCarThroughParkingLotsWithInValidCarTicket() throws Exception {
        ParkingAttendant parkingAttendant = new ParkingAttendant();
        ParkingLot parking = new ParkingLot(1);
        parkingAttendant.add(parking);
        Car car = new Car("xyz01","blue");
        String ticket = parking.parkCar(car);
        assertThrows(CarNotFoundException.class,() -> parkingAttendant.unPark("abc"));
    }

    @Test
    public void TestParkCarThrough2ParkingAttendant() throws Exception {
        ParkingAttendant parkingAttendant = new ParkingAttendant();
        ParkingAttendant secondParkingAttendant = new ParkingAttendant();
        ParkingLot parkingLot = new ParkingLot(2);
        parkingAttendant.add(parkingLot);
        secondParkingAttendant.add(parkingLot);

        Car car = new Car("xyz01","blue");
        parkingAttendant.parkCar(car);
        secondParkingAttendant.parkCar(car);
        assertTrue(parkingLot.isFull());
    }

    @Test
    public void TestParkCarThrough2ParkingAttendantWhenParkingLotIsFull() throws Exception {
        ParkingAttendant parkingAttendant = new ParkingAttendant();
        ParkingAttendant secondParkingAttendant = new ParkingAttendant();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingAttendant.add(parkingLot);
        secondParkingAttendant.add(parkingLot);

        Car car = new Car("xyz01","blue");
        parkingAttendant.parkCar(car);
        assertThrows(Exception.class,() -> secondParkingAttendant.parkCar(car));
    }

    @Test
    public void TestParkCarFromOneParkingAttendantAndUnParkCarFromSecondParkingAttendant() throws Exception {
        ParkingAttendant parkingAttendant = new ParkingAttendant();
        ParkingAttendant secondParkingAttendant = new ParkingAttendant();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingAttendant.add(parkingLot);
        secondParkingAttendant.add(parkingLot);

        Car carA = new Car("xyz01","blue");
        String ticket = parkingLot.parkCar(carA);
        Car getCar = secondParkingAttendant.unPark(ticket);
        assertEquals(carA,getCar);
    }




}
