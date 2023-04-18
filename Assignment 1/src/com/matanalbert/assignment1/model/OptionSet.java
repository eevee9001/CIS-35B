package com.matanalbert.assignment1.model;

public class OptionSet {
    private String setName;
    private Option[] opt;

    protected OptionSet() {}
    protected OptionSet(String setName, int size) {
        this.setName = setName;
    }

    protected String getSetName() {
        return setName;
    }

    protected void setSetName(String setName) {
        this.setName = setName;
    }

    protected Option[] getOpt() {
        return opt;
    }

    protected void setOpt(Option[] opt) {
        this.opt = opt;
    }

    protected Option getOptElement(int x) {
        return opt[x];
    }
    protected void setOptElement(Option opt, int x) {
        this.opt[x] = opt;
    }



    class Option {
         private String optionName;
         private float optionPrice;

         protected Option(){}
         protected Option(String optionName, float optionPrice) {}


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
         void printData() {

         }

         @Override
         public String toString() {
             final StringBuilder sb = new StringBuilder("Option{");
             sb.append("optionName='").append(optionName).append('\'');
             sb.append(", optionPrice=").append(optionPrice);
             sb.append('}');
             return sb.toString();
         }
     }

}
