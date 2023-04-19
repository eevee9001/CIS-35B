package com.matanalbert.assignment1.model;

public class Automotive {
    private String make;
    private String model;
    private float basePrice;
    private OptionSet[] opSet;

    public Automotive() {}

    public Automotive(String make, String model, double x, int y) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public OptionSet[] getOpSet() {
        return opSet;
    }

    public void setOpSet(OptionSet[] opSet) {
        this.opSet = opSet;
    }
}
