package com.matanalbert.assignment4.adapter;

public interface ChoiceAuto {
   String getOptionChoice(String modelName, String setName);
   float getOptionChoicePrice(String modelName, String setName);
   void setOptionChoice(String modelName, String setName, String optionName);
   float getTotalPrice(String modelName);
}
