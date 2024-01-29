import org.example.Car;
import org.example.ParkingLot;
import org.example.ParkingManager;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ParkingManagerTest {
    @Test
    public void TestCarParkWhenSlot1isAvailable() throws Exception {
        ParkingManager parkingManager = new ParkingManager(3);
        ParkingLot parkingLot = parkingManager.getAvailableSlot();
        parkingLot.parkCar(new Car("XYZ456","blue"), 0);
        ParkingLot parkingLot1 = parkingManager.getAvailableSlot();

        assertDoesNotThrow(() -> parkingLot.parkCar(new Car("XYZ456","blue"), 1));
    }
}
