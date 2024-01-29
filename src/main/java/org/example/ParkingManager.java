package org.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager {

    private List<ParkingLot> parkingLots;

    public ParkingManager(int noOfParkingLots) throws Exception {
        parkingLots = new ArrayList<ParkingLot>(noOfParkingLots);
        for(int i=0;i<noOfParkingLots;i++){
            parkingLots.add(new ParkingLot(1));
        }
    }

    public ParkingLot getAvailableSlot(){

        for(ParkingLot parkingLot : parkingLots){
           int index = parkingLot.findEmptySlot();
           if(index != -1){
               return parkingLot;
           }
        }
        return null;
    }


}
