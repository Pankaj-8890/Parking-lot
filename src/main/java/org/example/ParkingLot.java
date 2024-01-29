package org.example;


import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Car[] slots;
    private Map<Integer,String> cars;

    public ParkingLot(int numberOfSlots) throws Exception {
        if(numberOfSlots <=0)throw new Exception("slots can't be empty or negative");
        this.slots = new Car[numberOfSlots];
        this.cars = new HashMap<>();
    }

    private boolean find(int parkedIndex){

        if(cars.containsKey(parkedIndex)){
            return true;
        }else{
            return false;
        }
    }

    public void parkCar(Car car,int parkedIndex) throws Exception {

        int emptyIndex = findEmptySlot();
        if(parkedIndex < 0 || emptyIndex == -1 || find(parkedIndex))throw new Exception("can't parked");
        if (emptyIndex != -1) {
            slots[emptyIndex] = car;
            cars.put(emptyIndex,car.getColor());
        }
    }

    public void unParkCar(int unParkedIndex) throws Exception{

        if(!find(unParkedIndex))throw new Exception("car not found");
        slots[unParkedIndex] = null;
        cars.remove(unParkedIndex);
    }
    public int findEmptySlot() {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                return i;
            }
        }
        return -1;
    }
}