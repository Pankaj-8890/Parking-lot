import org.example.Car;
import org.example.ParkingLot;
import org.example.ParkingLots;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ParkingLotsTest {
    @Test
    public void TestPark4CarThroughParkingLotsWhenThere_is_4SlotsAvailable() throws Exception {
        ParkingLots parkingLots = new ParkingLots();
        ParkingLot parking = new ParkingLot(1);
        parkingLots.add(parking);
        ParkingLot parking1 = new ParkingLot(3);
        parkingLots.add(parking1);
        parkingLots.parkCar(new Car("XYZ4564","blue"));
        parkingLots.parkCar(new Car("XYZ4565","blue"));
        parkingLots.parkCar(new Car("XYZ4566","blue"));
        assertDoesNotThrow(() -> parkingLots.parkCar(new Car("XYZ4567","blue")));
    }

    @Test
    public void TestPark4CarThroughParkingLotsWhenThere_is_3SlotsAvailable() throws Exception {
        ParkingLots parkingLots = new ParkingLots();
        ParkingLot parking = new ParkingLot(1);
        parkingLots.add(parking);
        ParkingLot parking1 = new ParkingLot(2);
        parkingLots.add(parking1);
        parkingLots.parkCar(new Car("XYZ4564","blue"));
        parkingLots.parkCar(new Car("XYZ4565","blue"));
        parkingLots.parkCar(new Car("XYZ4567","blue"));
        assertThrows(Exception.class,() -> parkingLots.parkCar(new Car("XYZ4568","blue")));
    }
}
