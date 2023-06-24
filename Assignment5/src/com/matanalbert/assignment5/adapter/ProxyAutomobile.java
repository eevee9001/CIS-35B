package com.matanalbert.assignment5.adapter;

import com.matanalbert.assignment5.exception.AutoException;
import com.matanalbert.assignment5.scale.EditAuto;
import com.matanalbert.assignment5.scale.EditOption;
import com.matanalbert.assignment5.server.AutoServer;
import com.matanalbert.assignment5.util.FileIO;
import com.matanalbert.assignment5.util.Logger;
import com.matanalbert.assignment5.model.Automobile;
import com.matanalbert.assignment5.model.LHMAuto;

import java.io.IOException;

public abstract class ProxyAutomobile implements CreateAuto, UpdateAuto, ChoiceAuto, EditAuto {
    private static LHMAuto<String, Automobile> proxyAutomobiles = new LHMAuto<>();
    private final Logger logger = new Logger("log.txt");
    private final FileIO fileIO = new FileIO(logger);

    public void buildAuto(String fileName, FileType fileType) {
        boolean problemFixed = false;
        do {
            try {
                Automobile proxyAuto;

                switch (fileType) {
                    case TEXT -> {
                        proxyAuto = fileIO.buildAutoObject(fileName);
                    }
                    case PROPERTIES -> {
                        proxyAuto = fileIO.readPropertiesFile(fileName);
                    }
                    default -> {
                        // shouldn't happen
                        throw new IllegalArgumentException("Unknown file type");
                    }
                }
                proxyAutomobiles.create(proxyAuto.getModel(), proxyAuto);
                problemFixed = true;
            } catch (AutoException e) {
                logger.logException(e);
                fileName = e.fix1();
            } catch (IOException e) {
                logger.logException(e);
            }
        } while (!problemFixed);
    }

    public void printAuto(String modelName) {
        Automobile automobile = findAuto(modelName);
        if (automobile == null) {
            return;
        }
        automobile.printData();
    }

    public void updateOptionSetName(String modelName, String optionSetName, String newName) {
        Automobile automobile = findAuto(modelName);
        if (automobile == null) {
            return;
        }
        for (int i = 0; i < automobile.getOpSet().size(); i++) {
            if (automobile.getOpSetName(i).equals(optionSetName)) {
                automobile.setOpSetName(i, newName);
            }
        }
    }

    public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) {
        Automobile automobile = findAuto(modelName);
        if (automobile == null) {
            return;
        }
        for (int i = 0; i < automobile.getOpSet().size(); i++) {
            for (int j = 0; j < automobile.getOptionLength(i); j++) {
                if (automobile.getOpSetName(i).equals(optionSetName) &&
                        automobile.getOptName(i, j).equals(optionName)) {
                    automobile.setOptPrice(i, j, newPrice);
                }
            }
        }
        System.out.println(modelName + " not found");
    }

    @Override
    public String getOptionChoice(String modelName, String setName) {
        Automobile automobile = findAuto(modelName);
        if (automobile == null) {
            return null;
        }
        return automobile.getOptionChoice(setName);
    }

    @Override
    public float getOptionChoicePrice(String modelName, String setName) {
        Automobile automobile = findAuto(modelName);
        if (automobile == null) {
            return 0;
        }
        return automobile.getOptionChoicePrice(setName);
    }

    @Override
    public void setOptionChoice(String modelName, String setName, String optionName) {
        Automobile automobile = findAuto(modelName);
        if (automobile == null) {
            return;
        }
        automobile.setOptionChoice(setName, optionName);
    }

    @Override
    public float getTotalPrice(String modelName) {
        Automobile automobile = findAuto(modelName);
        if (automobile == null) {
            return 0;
        }
        return automobile.getTotalPrice();
    }

    /**
     * Finds an Automobile in a LinkedHashMap
     * @param modelName the Automobile's model to search for
     * @return the Automobile
     */
    private Automobile findAuto(String modelName) {
        for (Automobile automobile : proxyAutomobiles.values()) {
            if (automobile.getModel().equals(modelName)) {
                return automobile;
            }
        }
        System.out.println(modelName + " not found");
        return null;
    }

    public static LHMAuto<String, Automobile> getProxyAutomobiles() {
        return proxyAutomobiles;
    }

    @Override
    public void editThread(String modelName, int operation, String[] args) {
        new EditOption(modelName, operation, args);
    }

    public void addCreatedAutoToLHM(Automobile automobile) {
        String model = automobile.getModel();
        proxyAutomobiles.create(model, automobile);

    }
}
