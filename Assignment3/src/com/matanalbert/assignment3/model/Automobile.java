package com.matanalbert.assignment3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Automobile implements Serializable {
    private String make;
    private String model;
    private int year;
    private float basePrice;
    private List<OptionSet> opSet = new ArrayList<>();
    private List<Option> choice = new ArrayList<>();
    private static LHMAuto<String, String> LHMAuto = new LHMAuto<String, String>();

    public Automobile() {
    }

    public Automobile(String make, String model, int year, float basePrice) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.basePrice = basePrice;
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

    public float getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        return opSet.get(optionSetIndex);
    }

    /**
     * Constructs a single Option Set at a given index
     * @param setName new name for the option set
     */
    public void addOneOpSet(String setName, int setSize) {
        this.opSet.add(new OptionSet(setName, setSize));
    }

    public List<OptionSet> getOpSet() {
        return opSet;
    }

    public void setOpSet(List<OptionSet> opSet) { this.opSet = opSet; }

    public int getOpSetLength() {
        return opSet.size();
    }

    public int getOptionLength(int optionSetIndex) {
        return opSet.get(optionSetIndex).getOptLength();
    }

    public String getOpSetName(int optionSetIndex) {
        return opSet.get(optionSetIndex).getSetName();
    }

    public void setOpSetName(int optionSetIndex, String setName) {
        this.opSet.get(optionSetIndex).setSetName(setName);
    }

    public String getOptName(int optionSetIndex, int optionIndex) {
        return opSet.get(optionSetIndex).getOptName(optionIndex);
    }

    public void setOptName(int optionSetIndex, int optionIndex, String optName) {
        this.opSet.get(optionSetIndex).setOptName(optionIndex, optName);
    }

    public float getOptPrice(int optionSetIndex, int optionIndex) {
        return opSet.get(optionSetIndex).getOptPrice(optionIndex);
    }

    public void setOptPrice(int optionSetIndex, int optionIndex, float optPrice) {
        this.opSet.get(optionSetIndex).setOptPrice(optionIndex, optPrice);
    }

    /**
     * Calls an OptionSet method to construct a single Option at a given index
     * @param optionSetIndex index of Option Set to set
     * @param name Option name
     * @param price Option price
     */
    public void addOneOpSetOpt(int optionSetIndex, String name, float price) {
        this.opSet.get(optionSetIndex).addOption(name, price);
    }

    public void printMake() {
        System.out.printf("Make:  %s \n", make);
    }

    public void printModel() {
        System.out.printf("Model:  %s \n", model);
    }

    public void printYear() {
        System.out.printf("Year:  %s \n", year);
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
        this.opSet.get(optionSetIndex).printData();
    }

    public void printOneOpt(int optionSetIndex, int optionIndex) {
        this.opSet.get(optionSetIndex).printOneOption(optionIndex);
    }

    public void printData() {
        printMakeModel();
        printYear();
        printBasePrice();
        printOpSet();
    }

    /**
     * Finds an OptionSet by name and replaces the name
     * @param str search query
     * @param newSetName new set name
     */
    public void updateOpSetName(String str, String newSetName) {
        for (int i = 0; i < opSet.size(); i++) {
            if (i == str.indexOf(str)) {
                opSet.get(i).setSetName(newSetName);
            }
        }
    }

    /**
     * Finds an Option by name and replaces the name
     * @param str search query
     * @param newOptName new option name
     */
    public void updateOptName(String str, String newOptName) {
        for (int i = 0; i < opSet.size(); i++) {
            if (i == str.indexOf(str)) {
                opSet.get(i).setOptName(i, newOptName);
            }
        }
    }
    /**
     * Finds an Option's price by name and replaces the price
     * @param str search query
     * @param newOptPrice new option price
     */
    public void updateOptPrice(String str, float newOptPrice) {
        for (int i = 0; i < opSet.size(); i++) {
            if (i == str.indexOf(str)) {
                opSet.get(i).setOptPrice(i, newOptPrice);
            }
        }
    }

    /**
     * Deletes an OptionSet
     * @param optionSetIndex index of option set to delete
     */
    public void deleteOpSet(int optionSetIndex) {
        opSet.remove(optionSetIndex);
    }

    /**
     * Searches for an OptionSet by name and sets it to null
     * @param str name of Option Set
     */
    public void deleteOpSet(String str) {
        for (int i = 0; i < opSet.size(); i++) {
            OptionSet optionSet = opSet.get(i);
            if (optionSet == null) {
                return;
            }
            if (optionSet.getSetName().equals(str)) {
                opSet.remove(i);
                return;
            }
        }
    }

    /**
     * Deletes an Option and sets it to null
     * @param optionSetIndex index of option to replace with null
     */
    public void deleteOpt(int optionSetIndex, int optionIndex) {
        opSet.get(optionSetIndex).setOneOption(null, optionIndex);
    }

    /**
     * Searches for an Option by name and sets it to null
     * @param str name of Option
     */
    public void deleteOpt(String str) {
        for (int i = 0; i < opSet.size(); i++) {
            for (int j = 0; j < opSet.get(j).getOptLength(); j++) {
                if (j == str.indexOf(str)) {
                    opSet.get(j).setOpt(null);
                }
            }
        }
    }
}
