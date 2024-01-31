package org.example;

import java.util.Objects;
import java.util.UUID;

public class Slot {

    private Car car;
    private String ticket;

    public Slot() {
        this.car = null;
        this.ticket = null;
    }

    public boolean isSlotEmpty() {
        return car == null;
    }

    public String parkCar(Car car) throws CanNotParkedException {
        if (!isSlotEmpty()) {
            throw new CanNotParkedException("Slot is already occupied");
        }

        this.car = car;
        this.ticket = UUID.randomUUID().toString();
        return ticket;
    }

    public Car unParkCar(String ticket) throws CarNotFoundException {
        if (isSlotEmpty()) {
            throw new CarNotFoundException("Slot is empty");
        }

        if (!this.ticket.equals(ticket)) {
            throw new CarNotFoundException("Invalid ticket");
        }

        Car parkedCar = this.car;
        this.car = null;
        this.ticket = null;
        return parkedCar;
    }
    public boolean isValidId(String ticket) {
        return Objects.equals(this.ticket, ticket);
    }
}