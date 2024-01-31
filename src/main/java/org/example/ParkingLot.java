package org.example;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {

    private Car[] slots;
    private Map<String,Integer> cars;

    private String ticket;


    public ParkingLot(int numberOfSlots) throws Exception {
        if(numberOfSlots <=0)throw new Exception("slots can't be empty or negative");
        this.slots = new Car[numberOfSlots];
        this.cars = new HashMap<>();
    }

    public ParkingLot(int numberOfSlots,Car[] slots) throws Exception {
        if(numberOfSlots <=0)throw new Exception("slots can't be empty or negative");
        this.slots = slots;
    }

    private boolean find(String ticket){

        if(cars.containsKey(ticket)){
            return true;
        }else{
            return false;
        }
    }

    public String parkCar(Car car) throws Exception {

        int emptyIndex = findEmptySlot();
        if(emptyIndex == -1)throw new Exception("can't park");
        if (emptyIndex != -1) {
            slots[emptyIndex] = car;
            ticket = UUID.randomUUID().toString();
            cars.put(ticket,emptyIndex);
        }
        return ticket;
    }

    public Car unParkCar(String ticket) throws CarNotFoundException{
        if(!find(ticket))throw new CarNotFoundException("car not found");
        Car car = slots[cars.get(ticket)];
        slots[cars.get(ticket)] = null;
        cars.remove(ticket);
        return car;
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