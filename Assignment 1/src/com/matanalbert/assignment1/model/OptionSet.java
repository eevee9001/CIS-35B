package com.matanalbert.assignment1.model;

public class OptionSet {
    private String setName;
    private Option[] opt;

    public OptionSet() {}
    public OptionSet(String setName, int size) {
        this.setName = setName;
        opt = new Option[size];
    }

    protected String getSetName() {
        return setName;
    }

    protected void setSetName(String setName) {
        this.setName = setName;
    }

    protected Option getOptElement(int x) {
        return opt[x];
    }

    protected Option[] getOpt() {
        return opt;
    }

    protected void setOptElement(Option opt, int x) {
        this.opt[x] = opt;
    }

    protected void setOpt(Option[] opt) {
        this.opt = opt;
    }

    protected int getOptLength() {
        return opt.length;
    }

    protected void buildOption(int x, String name, float price) {
        // ***** to be written *****
    }

    protected String getOptName(int x) {
        return opt[x].getOptionName();
    }

    protected void setOptName(int x, String newOptName) {
        this.opt[x].setOptionName(newOptName);
    }

    protected float getOptPrice(int x) {
        return opt[x].getOptionPrice();
    }

    protected void setOptPrice(int x, float newOptPrice) {
        this.opt[x].setOptionPrice(newOptPrice);
    }

    protected void printData() {
        System.out.printf("Option Set name: %s\n", setName);
        for (Option option : opt) {
            option.printData();
        }
    }

    protected void printOptElement(int x) {
        System.out.printf("Option Set name: %s\n", setName);
        opt[x].printData();
    }



    static class Option {
         private String optionName;
         private float optionPrice;

         public Option(){}
         public Option(String optionName, float optionPrice) {
             this.optionName = optionName;
             this.optionPrice = optionPrice;
         }


         protected String getOptionName() {
             return optionName;
         }

         protected void setOptionName(String optionName) {
             this.optionName = optionName;
         }

         protected float getOptionPrice() {
             return optionPrice;
         }

         protected void setOptionPrice(float optionPrice) {
             this.optionPrice = optionPrice;
         }
         protected void printData() {
             System.out.printf("Option name: %s Option price: %f\n", optionName, optionPrice);
         }


    }

    public static void main(String[] args) {
        Option option = new Option("foo", 1f);
        option.printData();

        Option[] options = new Option[]{new Option("foo", 1f), new Option("bar",2f)};
        for (Option value : options) {
            value.printData();

        }

        }

}


