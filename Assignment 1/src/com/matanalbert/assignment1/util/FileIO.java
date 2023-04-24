package com.matanalbert.assignment1.util;

import com.matanalbert.assignment1.model.Automotive;

import java.io.*;

public class FileIO {

    /**
     * Reads a file and populates an Automotive object
     * @param fileName name of the file
     * @return the populated Automotive
     */
    public Automotive buildAutoObject(String fileName) throws IOException {
        // try with resources
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String make = reader.readLine();
            String model = reader.readLine();
            float basePrice = Float.parseFloat(reader.readLine());
            int numOptionSets = Integer.parseInt(reader.readLine());
            Automotive automotive = new Automotive(make, model, basePrice, numOptionSets);
            for (int optionSetIndex = 0; optionSetIndex < numOptionSets; optionSetIndex++) {
                String setName = reader.readLine();
                int setSize = Integer.parseInt(reader.readLine());
                automotive.setOneOpSet(optionSetIndex, setName, setSize);
                for (int optionIndex = 0; optionIndex < setSize; optionIndex++) {
                    String optionName = reader.readLine();
                    float optionPrice = Float.parseFloat(reader.readLine());
                    automotive.setOneOpSetOpt(optionSetIndex, optionIndex, optionName, optionPrice);
                }
            }
            return automotive;
        }
    }

    /**
     * Serializes Automotive data
     * @param automotive populated Automotive object
     * @param fileName file name to export to
     */
    public void writeData(Automotive automotive, String fileName) throws IOException {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            stream.writeObject(automotive);
        }
    }

    /**
     * De-Serializes Automotive data
     * @param serFileName name of .ser file
     * @return the de-serialized Automotive object
     */
    public Automotive readData(String serFileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(serFileName))) {
            return (Automotive) input.readObject();
        }
    }
}
