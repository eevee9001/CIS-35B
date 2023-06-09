package com.matanalbert.assignment4.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OptionSet implements Serializable {
    private String setName;
    private List<Option> opt = new ArrayList<>();
    private Option choice = null;

    OptionSet(String setName, int size) {
        this.setName = setName;
    }

    protected String getSetName() {
        return setName;
    }

    protected void setSetName(String setName) {
        this.setName = setName;
    }

    protected Option getOneOption(int optionSetIndex) {
        return opt.get(optionSetIndex);
    }

    protected List<Option> getOpt() {
        return opt;
    }

    protected void setOneOption(Option opt, int optionIndex) {
        this.opt.set(optionIndex, opt);
    }

    protected void setOpt(List<Option> opt) {
        this.opt = opt;
    }

    protected int getOptLength() {
        return opt.size();
    }

    protected String getOptName(int optionIndex) {
        return opt.get(optionIndex).getName();
    }

    protected void setOptName(int optionIndex, String newOptName) {
        float price = opt.get(optionIndex).getPrice();
        // Removing and adding on purpose to demonstrate concurrency corruption
        opt.remove(optionIndex);
        opt.add(new Option(newOptName, price));
//        this.opt.get(optionIndex).setName(newOptName);
        // above is a safe way of setting Option name
    }

    protected float getOptPrice(int optionIndex) {
        return opt.get(optionIndex).getPrice();
    }

    protected void setOptPrice(int optionIndex, float newOptPrice) {
        this.opt.get(optionIndex).setPrice(newOptPrice);
    }

    protected void printData() {
        System.out.printf("Option Set name: %s\n", setName);
        for (Option option : opt) {
            option.printData();
        }
    }

    protected void printOneOption(int optionIndex) {
        System.out.printf("Option Set name: %s\n", setName);
        opt.get(optionIndex).printData();
    }

    /**
     * Constructs a single Option at a given index
     * @param optionIndex index of Option
     * @param name Option name
     * @param price Option price
     */
    protected void buildOption(int optionIndex, String name, float price) {
        opt.set(optionIndex, new Option(name, price));
    }

    protected void addOption(String name, float price) {
        opt.add(new Option(name, price));
    }

    protected Option getOptionChoice() {
        return choice;
    }

    protected void setOptionChoice(String optionName) {
        for (Option option : opt) {
            if (option.getName().equals(optionName)) {
                choice = option;
                return;
            }
        }
    }
}


