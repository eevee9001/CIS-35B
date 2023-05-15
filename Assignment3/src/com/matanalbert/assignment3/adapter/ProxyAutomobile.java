package com.matanalbert.assignment3.adapter;

import com.matanalbert.assignment3.model.*;
import com.matanalbert.assignment3.exception.AutoException;
import com.matanalbert.assignment3.util.FileIO;
import com.matanalbert.assignment3.util.Logger;

public abstract class ProxyAutomobile implements CreateAuto, UpdateAuto {
    private static LHMAuto<String, Automobile> proxyAutomobiles = new LHMAuto<>();
    private final Logger logger = new Logger("log.txt");
    private final FileIO fileIO = new FileIO(logger);

    public void buildAuto(String fileName) {
        boolean problemFixed = false;
        do {
            try {
                Automobile proxyAuto = fileIO.buildAutoObject(fileName);
                String key = proxyAuto.getMake() + " " + proxyAuto.getModel() + " " + proxyAuto.getYear();
                proxyAutomobiles.create(key, proxyAuto);
                problemFixed = true;
            } catch (AutoException e) {
                logger.logException(e);
                fileName = e.fix1();
            }
        } while (!problemFixed);
    }

    public void printAuto(String modelName) {
        for (Automobile automobile : proxyAutomobiles.values()) {
                if (automobile.getModel().equals(modelName)) {
                    automobile.printData();
                    return;
                }
        }
        System.out.println(modelName + " not found");
    }

    public void updateOptionSetName(String modelName, String optionSetName, String newName) {
        for (Automobile automobile : proxyAutomobiles.values()) {
            if (automobile.getModel().equals(modelName)) {
                for (int i = 0; i < automobile.getOpSet().size(); i++) {
                    if (automobile.getOpSetName(i).equals(optionSetName)) {
                        automobile.setOpSetName(i, newName);
                    }
                }
                return;
            }
        }
        System.out.println(modelName + " not found");
    }

    public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) {
        for (Automobile automobile : proxyAutomobiles.values()) {
            if (automobile.getModel().equals(modelName)) {
                for (int i = 0; i < automobile.getOpSet().size(); i++) {
                    for (int j = 0; j < automobile.getOptionLength(i); j++) {
                        if (automobile.getOpSetName(i).equals(optionSetName) &&
                                automobile.getOptName(i, j).equals(optionName)) {
                            automobile.setOptPrice(i, j, newPrice);
                        }
                    }
                }
                return;
            }
        }
        System.out.println(modelName + " not found");
    }
}
