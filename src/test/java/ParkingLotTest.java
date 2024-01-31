import org.example.CanNotParkedException;
import org.example.Car;
import org.example.CarNotFoundException;
import org.example.ParkingLot;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ParkingLotTest {

    @Test
    public void TestParkingLotWhenWith0Slots() {
        assertThrows(Exception.class,() -> new ParkingLot(0));
    }

    @Test
    public void TestParkingLotWhenWithNegativeSlots() {
        assertThrows(Exception.class,() -> new ParkingLot(-1));
    }


    @Test
    public void TestParkCarInEmptySlot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.parkCar(new Car("ABC123","green"));
        parkingLot.parkCar(new Car("XYZ456","blue"));
        assertDoesNotThrow(() -> parkingLot.parkCar(new Car("ABC124","blue")));
    }




    @Test
    public void TestParkCarWhenCarIsAlreadyParked() throws Exception {
        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.parkCar(new Car("ABC123","green"));
        parkingLot.parkCar(new Car("XYZ456","blue"));
        parkingLot.parkCar(new Car("ABC124","green"));
        assertThrows(CanNotParkedException.class,() -> parkingLot.parkCar(new Car("XYZ457","blue")));
    }


    @Test
    public void TestUnParkCarTheValidCarTicket() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        String ticket = parkingLot.parkCar(new Car("ABC123","green"));
        assertDoesNotThrow(() ->  parkingLot.unParkCar(ticket));
    }

    @Test
    public void TestUnParkCarTheInValidTicket() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        String ticket = parkingLot.parkCar(new Car("ABC123","green"));
        assertThrows(CarNotFoundException.class,() -> parkingLot.unParkCar("abc"));
    }





}
