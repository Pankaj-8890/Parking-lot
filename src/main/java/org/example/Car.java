package org.example;

public class Car {
    private String vehicleNo;
    private String color;

    public Car(String vehicleNo, String color) {
        this.vehicleNo = vehicleNo;
        this.color = color;
    }
    public String getvehicleNo() {
        return vehicleNo;
    }

    public String getColor() {
        return color;
    }
}
