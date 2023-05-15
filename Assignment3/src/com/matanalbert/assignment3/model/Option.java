package com.matanalbert.assignment3.model;

import java.io.Serializable;

class Option implements Serializable {
    private String optionName;
    private float optionPrice;

    public Option() {
    }

    public Option(String optionName, float optionPrice) {
        this.optionName = optionName;
        this.optionPrice = optionPrice;
    }

    protected String getName() {
        return optionName;
    }

    protected void setName(String optionName) {
        this.optionName = optionName;
    }

    protected float getPrice() {
        return optionPrice;
    }

    protected void setPrice(float optionPrice) {
        this.optionPrice = optionPrice;
    }

    protected void printData() {
        System.out.printf("Option name: %s, Option price: %.2f\n", optionName, optionPrice);
    }
}
