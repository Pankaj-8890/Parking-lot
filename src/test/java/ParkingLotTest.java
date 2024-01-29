import org.example.Car;
import org.example.ParkingLot;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ParkingLotTest {

    @Test
    public void TestParkCarInEmptySlot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.parkCar(new Car("ABC123","green"), 0);
        parkingLot.parkCar(new Car("XYZ456","blue"), 1);
        assertDoesNotThrow(() -> parkingLot.parkCar(new Car("ABC123","blue"), 2));
    }

    @Test
    public void TestParkCarInvalidSlot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.parkCar(new Car("ABC123","blue"), 0);
        parkingLot.parkCar(new Car("XYZ456","green"), 1);
        parkingLot.parkCar(new Car("XYZ456","green"), 2);
        assertThrows(Exception.class,() -> parkingLot.parkCar(new Car("ABC123","blue"), 3));
    }

    @Test
    public void TestParkCarWhenCarIsAlreadyParked() throws Exception {
        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.parkCar(new Car("ABC123","green"), 0);
        parkingLot.parkCar(new Car("XYZ456","blue"), 1);
        parkingLot.parkCar(new Car("ABC123","green"), 2);
        assertThrows(Exception.class,() -> parkingLot.parkCar(new Car("XYZ456","blue"), 2));
    }

    @Test
    public void TestParkCarWithInvalidParkedIndex() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.parkCar(new Car("ABC123","green"), 0);
        assertThrows(Exception.class,() -> parkingLot.parkCar(new Car("XYZ456","blue"), -1));
    }

    @Test
    public void TestUnParkCarCorrectCar() throws Exception {
        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.parkCar(new Car("ABC123","green"), 0);
        parkingLot.parkCar(new Car("XYZ456","blue"), 1);
        parkingLot.parkCar(new Car("ABC123","green"), 2);
        assertDoesNotThrow(() ->  parkingLot.unParkCar(2));
    }

    @Test
    public void TestUnParkCarInCorrectCar() throws Exception {
        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.parkCar(new Car("ABC123","green"), 0);
        parkingLot.parkCar(new Car("XYZ456","blue"), 1);
        parkingLot.parkCar(new Car("ABC123","green"), 2);
        assertThrows(Exception.class,() -> parkingLot.unParkCar(3));
    }

}
