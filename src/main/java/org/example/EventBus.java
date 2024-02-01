package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventBus{

    private static final EventBus INSTANCE = new EventBus();

    private final Map<ObserverEvent, ArrayList<ISubscriber>> subscribers;


    private EventBus(){
        this.subscribers = new HashMap<>();
        for(ObserverEvent event : ObserverEvent.values()){
            subscribers.put(event,new ArrayList<>());
        }
    }

    public static EventBus instance(){
        return INSTANCE;
    }


    public void publish(ObserverEvent event,Object publisher){
        for(ISubscriber subscriber : subscribers.get(event)){
            subscriber.notify(event,publisher);
        }
    }

    public void subscribe(ISubscriber observer,ObserverEvent event){
        subscribers.get(event).add(observer);
    }
}
