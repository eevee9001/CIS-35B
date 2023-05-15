package com.matanalbert.assignment3.model;

import java.io.Serializable;

public class OptionSet implements Serializable {
    private String setName;
    private Option[] opt;
    // private ArrayList<Option> opt;

    OptionSet(String setName, int size) {
        this.setName = setName;
        opt = new Option[size];
    }

    protected String getSetName() {
        return setName;
    }

    protected void setSetName(String setName) {
        this.setName = setName;
    }

    protected Option getOneOption(int optionSetIndex) {
        return opt[optionSetIndex];
    }

    protected Option[] getOpt() {
        return opt;
    }

    protected void setOneOption(Option opt, int optionIndex) {
        this.opt[optionIndex] = opt;
    }

    protected void setOpt(Option[] opt) {
        this.opt = opt;
    }

    protected int getOptLength() {
        return opt.length;
    }

    protected String getOptName(int optionIndex) {
        return opt[optionIndex].getName();
    }

    protected void setOptName(int optionIndex, String newOptName) {
        this.opt[optionIndex].setName(newOptName);
    }

    protected float getOptPrice(int optionIndex) {
        return opt[optionIndex].getPrice();
    }

    protected void setOptPrice(int optionIndex, float newOptPrice) {
        this.opt[optionIndex].setPrice(newOptPrice);
    }

    protected void printData() {
        System.out.printf("Option Set name: %s\n", setName);
        for (Option option : opt) {
            option.printData();
        }
    }

    protected void printOneOption(int optionIndex) {
        System.out.printf("Option Set name: %s\n", setName);
        opt[optionIndex].printData();
    }

    /**
     * Constructs a single Option at a given index
     * @param optionIndex index of Option
     * @param name Option name
     * @param price Option price
     */
    protected void buildOption(int optionIndex, String name, float price) {
        opt[optionIndex] =new Option(name, price);
    }

}


