package com.matanalbert.assignment4.driver;

import com.matanalbert.assignment4.exception.AutoException;
import com.matanalbert.assignment4.model.Automobile;
import com.matanalbert.assignment4.util.FileIO;
import com.matanalbert.assignment4.util.Logger;

import java.io.IOException;

public class Driver1 {
    public static void main(String[] args) {
        FileIO f1 = new FileIO(new Logger("log.txt"));
        try {
            Automobile automobile = f1.buildAutoObject("Assignment 1/data/Car-1.txt");
            // Read file and populate automotive
            automobile.printData(); // Print data to system
            f1.writeData(automobile, "Assignment 1/data/Car-Serial1.ser");
            // Write data to .ser file
            Automobile newAutomobile = f1.readData("Assignment 1/data/Car-Serial1.ser");
            // populate a new automotive with data from .ser
            System.out.println("================================================================");
            newAutomobile.printData(); // print the new data
        } catch (IOException | ClassNotFoundException | AutoException e) {
            e.printStackTrace();
        }
    }
}
