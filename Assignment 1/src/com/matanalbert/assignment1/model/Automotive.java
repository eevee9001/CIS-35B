package com.matanalbert.assignment1.model;

import java.io.Serializable;

public class Automotive implements Serializable {
    private String make;
    private String model;
    private float basePrice;
    private OptionSet[] opSet;

    public Automotive() {
    }

    public Automotive(String make, String model, float basePrice, int numOptionSets) {
        this.make = make;
        this.model = model;
        this.basePrice = basePrice;
        this.opSet = new OptionSet[numOptionSets];
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

    /**
     * Retrieves a single Option Set
     * @param optionSetIndex index of Option Set to retrieve
     * @return the chosen Option Set
     */
    public OptionSet getOneOpSet(int optionSetIndex) {
        return opSet[optionSetIndex];
    }

    /**
     * Constructs a single Option Set at a given index
     * @param optionSetIndex index of Option Set to set
     * @param setName new name for the option set
     * @param setSize size of the option set
     */
    public void setOneOpSet(int optionSetIndex, String setName, int setSize) {
        this.opSet[optionSetIndex] = new OptionSet(setName, setSize);
    }

    public OptionSet[] getOpSet() {
        return opSet;
    }

    public void setOpSet(OptionSet[] opSet) { this.opSet = opSet; }

    public int getOpSetLength() {
        return opSet.length;
    }

    public int getOptionLength(int optionSetIndex) {
        return opSet[optionSetIndex].getOptLength();
    }

    public String getOpSetName(int optionSetIndex) {
        return opSet[optionSetIndex].getSetName();
    }

    public void setOpSetName(int optionSetIndex, String setName) {
        this.opSet[optionSetIndex].setSetName(setName);
    }

    public String getOptName(int optionSetIndex, int optionIndex) {
        return opSet[optionSetIndex].getOptName(optionIndex);
    }

    public void setOptName(int optionSetIndex, int optionIndex, String optName) {
        this.opSet[optionSetIndex].setOptName(optionIndex, optName);
    }

    public float getOptPrice(int optionSetIndex, int optionIndex) {
        return opSet[optionSetIndex].getOptPrice(optionIndex);
    }

    public void setOptPrice(int optionSetIndex, int optionIndex, float optPrice) {
        this.opSet[optionSetIndex].setOptPrice(optionIndex, optPrice);
    }

    /**
     * Calls an OptionSet method to construct a single Option at a given index
     * @param optionSetIndex index of Option Set to set
     * @param optionIndex index of Option to set
     * @param name Option name
     * @param price Option price
     */
    public void setOneOpSetOpt(int optionSetIndex, int optionIndex, String name, float price) {
        this.opSet[optionSetIndex].buildOption(optionIndex, name, price);
    }

    public void printMake() {
        System.out.printf("Make:  %s \n", make);
    }

    public void printModel() {
        System.out.printf("Model:  %s \n", model);
    }

    public void printBasePrice() {
        System.out.printf("Base price:  %.2f\n", basePrice);
    }

    public void printMakeModel() {
        printMake();
        printModel();
    }

    public void printOpSet() {
        for (OptionSet set : opSet) {
            set.printData();
        }
    }

    public void printOneOpSet(int optionSetIndex) {
        this.opSet[optionSetIndex].printData();
    }

    public void printOneOpt(int optionSetIndex, int optionIndex) {
        this.opSet[optionSetIndex].printOneOption(optionIndex);
    }

    public void printData() {
        printMakeModel();
        printBasePrice();
        printOpSet();
    }

    /**
     * Finds an OptionSet by name and replaces the name
     * @param str search query
     * @param newSetName new set name
     */
    public void updateOpSetName(String str, String newSetName) {
        for (int i = 0; i < opSet.length; i++) {
            if (i == str.indexOf(str)) {
                opSet[i].setSetName(newSetName);
            }
        }
    }

    /**
     * Finds an Option by name and replaces the name
     * @param str search query
     * @param newOptName new option name
     */
    public void updateOptName(String str, String newOptName) {
        for (int i = 0; i < opSet.length; i++) {
            if (i == str.indexOf(str)) {
                opSet[i].setOptName(i, newOptName);
            }
        }
    }
    /**
     * Finds an Option's price by name and replaces the price
     * @param str search query
     * @param newOptPrice new option price
     */
    public void updateOptPrice(String str, float newOptPrice) {
        for (int i = 0; i < opSet.length; i++) {
            if (i == str.indexOf(str)) {
                opSet[i].setOptPrice(i, newOptPrice);
            }
        }
    }

    /**
     * Deletes an OptionSet and sets it to null
     * @param optionSetIndex index of option set to replace with null
     */
    public void deleteOpSet(int optionSetIndex) {
        opSet[optionSetIndex] = null;
    }

    /**
     * Searches for an OptionSet by name and sets it to null
     * @param str name of Option Set
     */
    public void deleteOpSet(String str) {
        for (int i = 0; i < opSet.length; i++) {
            OptionSet optionSet = opSet[i];
            if (optionSet == null) {
                return;
            }
            if (optionSet.getSetName().equals(str)) {
                opSet[i] = null;
                return;
            }
        }
    }

    /**
     * Deletes an Option and sets it to null
     * @param optionSetIndex index of option to replace with null
     */
    public void deleteOpt(int optionSetIndex, int optionIndex) {
        opSet[optionSetIndex].setOneOption(null, optionIndex);
    }

    /**
     * Searches for an Option  by name and sets it to null
     * @param str name of Option
     */
    public void deleteOpt(String str) {
        for (int i = 0; i < opSet.length; i++) {
            for (int j = 0; j < opSet[j].getOptLength(); j++) {
                if (j == str.indexOf(str)) {
                    opSet[j].setOpt(null);
                }
            }
        }
    }
}
