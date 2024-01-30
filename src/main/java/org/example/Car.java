package org.example;

public class Car {
    private String vehicleNo;
    private String color;

    public Car(String vehicleNo, String color) {
        this.vehicleNo = vehicleNo;
        this.color = color;
    }


    public String vehicleNo() {
        return new String(this.vehicleNo);
    }

    public String color() {
        return new String(this.color);
    }
}
