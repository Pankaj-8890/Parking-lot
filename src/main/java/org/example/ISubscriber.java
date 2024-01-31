package org.example;

public interface ISubscriber {

    default void notify(ObserverEvent event, Object publisher){
        System.out.println(event.toString());
    }
}
