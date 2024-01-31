package org.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingLots {

    private List<ParkingLot> parkingLots;


    public ParkingLots(){
        parkingLots = new ArrayList<>();
    }

    public void add(ParkingLot parkingLot){
        parkingLots.add(parkingLot);
    }


    public void parkCar(Car car) throws Exception {

        for(ParkingLot parkingLot : parkingLots){
            int index = parkingLot.findEmptySlot();
            if(index != -1){
                parkingLot.parkCar(car);
                return;
            }
        }
        throw new Exception("can't parked");
    }
    public Car unPark(String id) throws CarNotFoundException {
        for (ParkingLot parkingLot: parkingLots) {
            Car car = parkingLot.unParkCar(id);
            if (car != null) {
                return car;
            }
        }
        throw new CarNotFoundException("No car parked with this id.");
    }
}
