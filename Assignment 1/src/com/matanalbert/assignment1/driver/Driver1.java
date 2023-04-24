package com.matanalbert.assignment1.driver;

import com.matanalbert.assignment1.model.Automotive;
import com.matanalbert.assignment1.util.FileIO;

import java.io.IOException;

public class Driver1 {
    public static void main(String[] args) {
        FileIO f1 = new FileIO();
        try {
            Automotive automotive = f1.buildAutoObject("Assignment 1/data/Car-1.txt");
            // Read file and populate automotive
            automotive.printData(); // Print data to system
            f1.writeData(automotive, "Assignment 1/data/Car-Serial1.ser");
            // Write data to .ser file
            Automotive newAutomotive = f1.readData("Assignment 1/data/Car-Serial1.ser");
            // populate a new automotive with data from .ser
            System.out.println("================================================================");
            newAutomotive.printData(); // print the new data
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
