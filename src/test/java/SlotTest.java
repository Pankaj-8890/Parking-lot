import org.example.CanNotParkedException;
import org.example.Car;
import org.example.CarNotFoundException;
import org.example.Slot;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class SlotTest {

    @Test
    public void TestIsSlotEmptyReturnTrueWhenSlotIsEmpty() {

        Slot slot = new Slot();
        assertTrue(slot.isSlotEmpty());
    }

    @Test
    public void TestAbleToParkTheCarInEmptySlot() throws CanNotParkedException {

        Car car = new Car("abcd1234","blue");
        Slot slot = new Slot();
        slot.parkCar(car);
        assertFalse(slot.isSlotEmpty());
    }

    @Test
    public void TestThrowsExceptionWhenParkTheCarInNonEmptySlot() throws CanNotParkedException {

        Car car = new Car("abcd1234","blue");
        Slot slot = new Slot();
        slot.parkCar(car);
        assertThrows(CanNotParkedException.class,()-> slot.parkCar(new Car("abcd123456","black")));
    }

    @Test
    public void TestThrowsExceptionWhenUnParkCarWhenSlotIsEmpty(){

        Slot slot = new Slot();
        String ticket = UUID.randomUUID().toString();
        assertThrows(CarNotFoundException.class,()-> slot.unParkCar(ticket));
    }

    @Test
    public void TestAbleToUnParkCarCorrectCar() throws CanNotParkedException, CarNotFoundException {

        Car car = new Car("abcd1234","blue");
        Slot slot = new Slot();
        String ticket = slot.parkCar(car);
        Car getCar = slot.unParkCar(ticket);
        assertEquals(car,getCar);
    }

}
