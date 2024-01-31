package org.example;
public class ParkingLot {

    private Slot[] slots;

    public ParkingLot(int numberOfSlots) throws Exception {
        if (numberOfSlots <= 0) {
            throw new Exception("Slots can't be empty or negative");
        }
        this.slots = new Slot[numberOfSlots];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new Slot();
        }
    }

    public String parkCar(Car car) throws CanNotParkedException {
        int emptySlot = findEmptySlot();
        if (emptySlot == -1) {
            throw new CanNotParkedException("Parking lot is full");
        }
        return slots[emptySlot].parkCar(car);
    }


    private Slot getParkedCarSlot(String ticket) throws CarNotFoundException {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].isValidId(ticket)) {
                return slots[i];
            }
        }
        throw new CarNotFoundException("Car not found.");
    }

    public Car unParkCar(String id) throws CarNotFoundException {
        Slot slot = this.getParkedCarSlot(id);
        if (slot == null) {
            throw new CarNotFoundException("Car not found.");
        }
        return slot.unParkCar(id);
    }

    public boolean isFull(){
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].isSlotEmpty()) {
                return false;
            }
        }
        return true;
    }
    public int findEmptySlot() {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null || slots[i].isSlotEmpty()) {
                return i;
            }
        }
        return -1;
    }
}
