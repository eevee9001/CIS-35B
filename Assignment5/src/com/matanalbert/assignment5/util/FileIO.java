package com.matanalbert.assignment5.util;

import com.matanalbert.assignment5.exception.AutoException;
import com.matanalbert.assignment5.model.Automobile;
import com.matanalbert.assignment5.model.Option;
import com.matanalbert.assignment5.model.OptionSet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileIO {

    private final Logger logger;

    public FileIO(Logger logger) {
        this.logger = logger;
    }

    /**
     * Reads a file and populates an Automotive object
     *
     * @param fileName name of the file
     * @return the populated Automotive
     * @throws AutoException when fails to read file
     */
    public Automobile buildAutoObject(String fileName) throws AutoException {
        // try with resources
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String make = safeReadStringValue(reader, "Make");
            String model = safeReadStringValue(reader, "Model");
            int year = safeReadIntValue(reader, "Year");
            float basePrice = safeReadFloatValue(reader, "basePrice");
            int numOptionSets = safeReadIntValue(reader, "numOptionSets");
            Automobile automobile = new Automobile(make, model, year, basePrice);
            for (int optionSetIndex = 0; optionSetIndex < numOptionSets; optionSetIndex++) {
                String setName = safeReadStringValue(reader, "setName");
                int setSize = safeReadIntValue(reader, "setSize");
                automobile.addOneOpSet(setName, setSize);
                for (int optionIndex = 0; optionIndex < setSize; optionIndex++) {
                    String optionName = safeReadStringValue(reader, "optionName");
                    float optionPrice = safeReadFloatValue(reader, "optionPrice");
                    automobile.addOneOpSetOpt(optionSetIndex, optionName, optionPrice);
                }
            }
            return automobile;
        } catch (IOException e) {
            throw new AutoException(AutoException.INVALID_FILE, "Could not read from file " + fileName);
        }
    }

    /**
     * Serializes Automotive data
     *
     * @param automobile populated Automotive object
     * @param fileName   file name to export to
     * @throws IOException when the file could not be written to
     */
    public void writeData(Automobile automobile, String fileName) throws IOException {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            stream.writeObject(automobile);
        }
    }

    /**
     * De-Serializes Automotive data
     *
     * @param serFileName name of .ser file
     * @return the de-serialized Automotive object
     * @throws IOException            when the file could not be read
     * @throws ClassNotFoundException when Automobile not read
     */
    public Automobile readData(String serFileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(serFileName))) {
            return (Automobile) input.readObject();
        }
    }

    /**
     * Reads a String and, if missing, logs an exception
     *
     * @param reader BufferedReader
     * @param name   name of expected Automobile String property
     * @return a prompt to input the missing expected String property
     */
    private String safeReadStringValue(BufferedReader reader, String name) {
        try {
            return readStringValue(reader, name);
        } catch (AutoException e) {
            logger.logException(e);
            return e.fix2(name);
        }
    }

    /**
     * Reads the value of a String
     *
     * @param reader BufferedReader
     * @param name   name of expected Automobile String property
     * @return the String that was read
     * @throws AutoException if String value is missing
     */
    private String readStringValue(BufferedReader reader, String name) throws AutoException {
        try {
            String line = reader.readLine();
            if (line == null) {
                throw new AutoException(AutoException.MISSING_STRING_VALUE, "Missing '" + name + "'");
            }
            return line;
        } catch (IOException e) {
            throw new AutoException(AutoException.MISSING_STRING_VALUE, "Missing '" + name + "'");
        }
    }

    /*
        Similar to previous 2 methods
     */
    private float safeReadFloatValue(BufferedReader reader, String name) {
        try {
            return readFloatValue(reader, name);
        } catch (AutoException e) {
            logger.logException(e);
            return e.fix3(name);
        }
    }

    private float readFloatValue(BufferedReader reader, String name) throws AutoException {
        try {
            return Float.parseFloat(reader.readLine());
        } catch (IOException | NullPointerException | NumberFormatException e) {
            throw new AutoException(AutoException.MISSING_FLOAT_VALUE, "Missing '" + name + "'");
        }
    }

    private int safeReadIntValue(BufferedReader reader, String name) {
        try {
            return readIntValue(reader, name);
        } catch (AutoException e) {
            logger.logException(e);
            return e.fix4(name);
        }
    }

    private int readIntValue(BufferedReader reader, String name) throws AutoException {
        try {
            int value = Integer.parseInt(reader.readLine());
            if (value <= 0) {
                throw new AutoException(AutoException.MISSING_INT_VALUE, "Missing '" + name + "'");
            }
            return value;
        } catch (IOException | NumberFormatException e) {
            throw new AutoException(AutoException.MISSING_INT_VALUE, "Missing '" + name + "'");
        }
    }

    public Automobile buildFromPropertiesFile(String fileName) throws IOException {
        Properties properties = new Properties();

        try (FileInputStream in = new FileInputStream(fileName);) {
            properties.load(in);
        }
        return buildFromProperties(properties);
    }

    public Automobile buildFromProperties(Properties properties) throws IOException {

        String carMake = properties.getProperty("carMake");
        String carModel = properties.getProperty("carModel");
        String carYear = properties.getProperty("carYear");
        String basePrice = properties.getProperty("basePrice");
        Automobile automobile = new Automobile(carMake, carModel, Integer.parseInt(carYear), Float.parseFloat(basePrice));

        for (int i = 1; ; i++) {
            String optionSetName = properties.getProperty("optionSet" + i);
            if (optionSetName == null) {
                break;
            }
            List<Option> options = new ArrayList<>();
            for (char c = 'A'; ; c++) {
                String optionName = properties.getProperty("optionName" + i + c);
                if (optionName == null) {
                    break;
                }
                String optionPrice = properties.getProperty("optionPrice" + i + c);
                if (optionPrice == null) {
                    throw new IllegalStateException("Missing option price for ");
                }

                options.add(new Option(optionName, Float.parseFloat(optionPrice)));
            }
            OptionSet optionSet = new OptionSet(optionSetName, options);
            automobile.addOneOpSet(optionSetName, optionSet);
        }
        return automobile;
    }

    public static void main(String[] args) throws IOException {
        FileIO fileIO = new FileIO(new Logger("log.txt"));
        Automobile automobile = fileIO.buildFromPropertiesFile("New-Car.properties");

        automobile.printData();
    }
}
