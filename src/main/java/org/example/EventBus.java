package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventBus{

    private static final EventBus INSTANCE = new EventBus();

    private final Map<ObserverEvent, ArrayList<ISubscriber>> subscribers;

    private final Map<ArrayList<ParkingLot>,ObserverEvent> specificSubscribers;

    private final Map<ParkingLot,ArrayList<ISubscriber>> demo;
    private EventBus(){
        this.subscribers = new HashMap<>();
        this.specificSubscribers = new HashMap<>();
        this.demo = new HashMap<>();
        for(ObserverEvent event : ObserverEvent.values()){
            subscribers.put(event,new ArrayList<>());
        }

    }

    public static EventBus instance(){
        return INSTANCE;
    }


    public void publish(ObserverEvent event,Object publisher){

        for(ISubscriber subscriber : subscribers.get(event)){

            if(subscriber.getClass() == ParkingAttendant.class){
                for(ISubscriber parkingAttendant : demo.get(publisher)){
                    if(subscriber == parkingAttendant){
                        subscriber.notify(event,publisher);
                    }
                }
            }else{
                subscriber.notify(event,publisher);
            }


        }
    }

    public void subscribe(ISubscriber observer,ObserverEvent event,Object... publisher){

        if(observer.getClass() == ParkingAttendant.class){
            if(demo.get((ParkingLot) publisher[0]).isEmpty()){
                demo.put((ParkingLot) publisher[0],new ArrayList<>());
            }
            demo.get((ParkingLot) publisher[0]).add(observer);
        }
        subscribers.get(event).add(observer);
    }

}
