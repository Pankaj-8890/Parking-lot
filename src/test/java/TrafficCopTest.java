import org.example.*;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TrafficCopTest {

    @Test
    public void TestNotifyTheTrafficCopWhenParkingLotFull() throws Exception {
        ISubscriber trafficCop = mock(TrafficCop.class);
        ParkingLot parkingLot = new ParkingLot(1);
        Car carA = new Car("xyz01","blue");

        EventBus.instance().subscribe(trafficCop,ObserverEvent.FULL);
        parkingLot.parkCar(carA);

        verify(trafficCop).notify(ObserverEvent.FULL,parkingLot);

    }

    @Test
    public void TestNotifyTheTrafficCopWhenParkingLotisEmpty() throws Exception {
        ISubscriber trafficCop = mock(TrafficCop.class);
        ParkingLot parkingLot = new ParkingLot(1);
        Car carA = new Car("xyz01","blue");

        EventBus.instance().subscribe(trafficCop,ObserverEvent.EMPTY);
        String ticketA = parkingLot.parkCar(carA);
        parkingLot.unParkCar(ticketA);

        verify(trafficCop).notify(ObserverEvent.EMPTY,parkingLot);


    }
}
