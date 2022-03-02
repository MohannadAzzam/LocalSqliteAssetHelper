package com.example.database;

public class Car {
    private int id;
    private String model;
    private String color;
    private double DPL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getDPL() {
        return DPL;
    }

    public void setDPL(double DPL) {
        this.DPL = DPL;
    }

    public Car(String model, String color, double DPL) {
        this.model = model;
        this.color = color;
        this.DPL = DPL;
    }

    public Car(int id, String model, String color, double DPL) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.DPL = DPL;
    }
}
