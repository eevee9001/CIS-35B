package com.matanalbert.assignment2.util;

import com.matanalbert.assignment2.exception.AutoException;
import com.matanalbert.assignment2.model.Automobile;

import java.io.*;

public class FileIO {

    /**
     * Reads a file and populates an Automotive object
     * @param fileName name of the file
     * @return the populated Automotive
     */
    public Automobile buildAutoObject(String fileName) throws AutoException {
        // try with resources
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String make = reader.readLine();
            String model = reader.readLine();
            float basePrice = Float.parseFloat(reader.readLine());
            int numOptionSets = Integer.parseInt(reader.readLine());
            Automobile automobile = new Automobile(make, model, basePrice, numOptionSets);
            for (int optionSetIndex = 0; optionSetIndex < numOptionSets; optionSetIndex++) {
                String setName = reader.readLine();
                int setSize = Integer.parseInt(reader.readLine());
                automobile.setOneOpSet(optionSetIndex, setName, setSize);
                for (int optionIndex = 0; optionIndex < setSize; optionIndex++) {
                    String optionName = reader.readLine();
                    float optionPrice = Float.parseFloat(reader.readLine());
                    automobile.setOneOpSetOpt(optionSetIndex, optionIndex, optionName, optionPrice);
                }
            }
            return automobile;
        } catch (IOException e) {
            throw new AutoException(AutoException.INVALID_FILE, "Could not read from file " + fileName);
        }
    }

    /**
     * Serializes Automotive data
     * @param automobile populated Automotive object
     * @param fileName file name to export to
     */
    public void writeData(Automobile automobile, String fileName) throws IOException {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            stream.writeObject(automobile);
        }
    }

    /**
     * De-Serializes Automotive data
     * @param serFileName name of .ser file
     * @return the de-serialized Automotive object
     */
    public Automobile readData(String serFileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(serFileName))) {
            return (Automobile) input.readObject();
        }
    }

    private BufferedReader openFile(String fileName) throws AutoException {
        try {
            return new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new AutoException(AutoException.INVALID_FILE, "File not found: " + fileName);
        }
    }

}
