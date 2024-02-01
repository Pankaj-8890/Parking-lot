package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class ParkingAttendant implements ISubscriber{

    private final List<ParkingLot> parkingLots;

    private  Statergy statergy;

    public ParkingAttendant(){
        parkingLots = new ArrayList<>();
        this.statergy = Statergy.NEAREST;
    }

    public void add(ParkingLot parkingLot){
        parkingLots.add(parkingLot);
    }

    public void changeStatery(Statergy statergy){
        this.statergy = statergy;
    }

    public String parkCar(Car car) throws Exception {

        List<ParkingLot> parkingLotsTo = new ArrayList<>(parkingLots);
        if(statergy == Statergy.FARTHEST){
            Collections.reverse(parkingLotsTo);
        }
        for(ParkingLot parkingLot : parkingLotsTo){
            try{
                String index = parkingLot.parkCar(car);
                if(index != null) return index;

            }catch (CanNotParkedException ex){
                System.out.println(ex.getMessage());
            }

        }
        throw new Exception("can't parked");
    }
    public Car unPark(String id) throws CarNotFoundException {
        for (ParkingLot parkingLot: parkingLots) {
            try{
                Car car = parkingLot.unParkCar(id);
                if (car != null) {
                    return car;
                }
            }catch (CarNotFoundException ex){
                System.out.println(ex.getMessage());
            }

        }
        throw new CarNotFoundException("No car parked with this id.");
    }

}
