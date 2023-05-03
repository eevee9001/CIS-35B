package com.matanalbert.assignment2.adapter;

import com.matanalbert.assignment2.model.*;
import com.matanalbert.assignment2.util.FileIO;

import java.io.IOException;

public abstract class ProxyAutomobile implements CreateAuto, UpdateAuto {
    private static Automobile proxyAuto;

    public void buildAuto(String fileName) throws IOException {
        FileIO f1 = new FileIO();
        proxyAuto = f1.buildAutoObject(fileName);

    }

    public void printAuto(String modelName) {
        if (proxyAuto.getModel().equals(modelName)) {
            proxyAuto.printData();
        }
        else {
            System.out.println(modelName + "not found");
        }
    }

    public void updateOptionSetName(String modelName, String optionSetName, String newName) {
        if (proxyAuto.getModel().equals(modelName)) {
            for (int i = 0; i < proxyAuto.getOpSet().length; i++) {
                if (proxyAuto.getOpSetName(i).equals(optionSetName)) {
                    proxyAuto.setOpSetName(i, newName);
                }
            }
        }
    }

    public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) {
        if(proxyAuto.getModel().equals(modelName)) {
            for (int i = 0; i < proxyAuto.getOpSet().length; i++) {
                for (int j = 0; j < proxyAuto.getOptionLength(i); j++) {
                    if (proxyAuto.getOpSetName(i).equals(optionSetName) && proxyAuto.getOptName(i,j).equals(optionName)) {
                        proxyAuto.setOptPrice(i, j, newPrice);
                    }
                }
            }
        }
    }


}
