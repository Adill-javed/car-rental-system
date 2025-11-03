package com.example.carrental.model;

public class Car {
    private int id;
    private String name;
    private double basePricePerDay;
    private boolean available;

    public Car() {}

    public Car(int id, String name, double basePricePerDay, boolean available) {
        this.id = id;
        this.name = name;
        this.basePricePerDay = basePricePerDay;
        this.available = available;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBasePricePerDay() { return basePricePerDay; }
    public void setBasePricePerDay(double basePricePerDay) { this.basePricePerDay = basePricePerDay; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
