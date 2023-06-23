package com.matanalbert.assignment5.util;

import com.matanalbert.assignment5.exception.AutoException;
import com.matanalbert.assignment5.model.Automobile;

import java.io.*;
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
     * @param automobile    populated Automotive object
     * @param fileName      file name to export to
     * @throws IOException  when the file could not be written to
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
     * @throws IOException when the file could not be read
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
     * @param name name of expected Automobile String property
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
     * @param name name of expected Automobile String property
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

    public void readPropertiesFile(String fileName) throws IOException {
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream(fileName);
        properties.load(in);
        in.close();

        String carMake = properties.getProperty("carMake");
        if (!carMake.equals(null)) {
            String carModel = properties.getProperty("carModel");
            String carYear = properties.getProperty("carYear");
            String basePrice = properties.getProperty("basePrice");
            String numOptionSets = properties.getProperty("numOptionSets");

            String optionSet1 = properties.getProperty("optionSet1");
            String numOptions1 = properties.getProperty("numOptions1");
            String optionValue1A = properties.getProperty("optionValue1A");
            String priceOption1A = properties.getProperty("priceOption1A");
            String optionValue1B = properties.getProperty("optionValue1B");
            String priceOption1B = properties.getProperty("priceOption1B");
            String optionValue1C = properties.getProperty("optionValue1C");
            String priceOption1C = properties.getProperty("priceOption1C");
            String optionValue1D = properties.getProperty("optionValue1D");
            String priceOption1D = properties.getProperty("priceOption1D");
            String optionValue1E = properties.getProperty("optionValue1E");
            String priceOption1E = properties.getProperty("priceOption1E");

            String optionSet2 = properties.getProperty("optionSet2");
            String numOptions2 = properties.getProperty("numOptions2");
            String optionValue2A = properties.getProperty("optionValue2A");
            String priceOption2A = properties.getProperty("priceOption2A");
            String optionValue2B = properties.getProperty("optionValue2B");
            String priceOption2B = properties.getProperty("priceOption2B");

            String optionSet3 = properties.getProperty("optionSet3");
            String numOptions3 = properties.getProperty("numOptions3");
            String optionValue3A = properties.getProperty("optionValue3A");
            String priceOption3A = properties.getProperty("priceOption3A");
            String optionValue3B = properties.getProperty("optionValue3B");
            String priceOption3B = properties.getProperty("priceOption3B");
            String optionValue3C = properties.getProperty("optionValue3C");
            String priceOption3C = properties.getProperty("priceOption3C");

            String optionSet4 = properties.getProperty("optionSet4");
            String numOptions4 = properties.getProperty("numOptions4");
            String optionValue4A = properties.getProperty("optionValue4A");
            String priceOption4A = properties.getProperty("priceOption4A");
            String optionValue4B = properties.getProperty("optionValue4B");
            String priceOption4B = properties.getProperty("priceOption4B");

            String optionSet5 = properties.getProperty("optionSet5");
            String numOptions5 = properties.getProperty("numOptions5");
            String optionValue5A = properties.getProperty("optionValue5A");
            String priceOption5A = properties.getProperty("priceOption5A");
            String optionValue5B = properties.getProperty("optionValue5B");
            String priceOption5B = properties.getProperty("priceOption5B");

            // arbitrary minimum number of option sets will be 5 for simplicity
        }
    }
}
