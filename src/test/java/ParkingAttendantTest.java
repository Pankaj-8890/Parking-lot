import org.example.*;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        String ticket = parkingAttendant.parkCar(carA);
        Car getCar = secondParkingAttendant.unPark(ticket);
        assertEquals(carA,getCar);
    }

    @Test
    public void TestParkCarOntheFarthestParkingLot() throws Exception {
        ParkingAttendant parkingAttendant = new ParkingAttendant();
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot anotherParkingLot = new ParkingLot(3);
        parkingAttendant.changeStatery(Statergy.FARTHEST);
        parkingAttendant.add(parkingLot);
        parkingAttendant.add(anotherParkingLot);
        Car carA = new Car("xyz01","blue");
        Car carB = new Car("xyz01","blue");
        Car carC = new Car("xyz01","blue");

        parkingAttendant.parkCar(carA);
        parkingAttendant.parkCar(carB);
        parkingAttendant.parkCar(carC);

        assertFalse(parkingLot.isFull());
        assertTrue(anotherParkingLot.isFull());
    }

    @Test
    public void TestNotifyTheParkingAttendantWhenParkingLotFull() throws Exception {
        ISubscriber parkingAttendant = mock(ParkingAttendant.class);
        ParkingLot parkingLot = new ParkingLot(1);
        Car carA = new Car("xyz01","blue");

        EventBus.instance().subscribe(parkingAttendant,ObserverEvent.FULL);
        parkingLot.parkCar(carA);

        verify(parkingAttendant).notify(ObserverEvent.FULL,parkingLot);


    }

    @Test
    public void TestNotifyTheParkingAttendantWhenParkingLotisNotFull() throws Exception {
        ISubscriber parkingAttendant = mock(ParkingAttendant.class);
        ParkingLot parkingLot = new ParkingLot(2);
        Car carA = new Car("xyz01","blue");

        EventBus.instance().subscribe(parkingAttendant,ObserverEvent.EMPTY);
        parkingLot.parkCar(carA);

        verify(parkingAttendant).notify(ObserverEvent.EMPTY,parkingLot);


    }

    @Test
    public void TestNotifyTheParkingAttendantWhenUnParkCarAndParkingLotIsNotFull() throws Exception {
        ISubscriber parkingAttendant = mock(ParkingAttendant.class);
        ParkingLot parkingLot = new ParkingLot(2);
        Car carA = new Car("xyz01","blue");
        Car carB = new Car("xyz01","blue");
        EventBus.instance().subscribe(parkingAttendant,ObserverEvent.FULL);
        String ticketA = parkingLot.parkCar(carA);
        String ticketB = parkingLot.parkCar(carB);

        EventBus.instance().subscribe(parkingAttendant,ObserverEvent.EMPTY);
        parkingLot.unParkCar(ticketB);

        verify(parkingAttendant).notify(ObserverEvent.EMPTY,parkingLot);

    }



}
