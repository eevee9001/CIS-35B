package com.matanalbert.assignment2.driver;

import com.matanalbert.assignment2.adapter.*;
import java.io.IOException;

/**
 * Sample data files are in data directory :
 * ValidCar.txt: A complete automobile definition
 * IncompleteCar.txt: An example of an incomplete automobile that generates exceptions
 */
public class Driver2A {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: Driver2A ValidCar.txt IncompleteCar.txt");
            System.exit(1);
        }
        CreateAuto create = new BuildAuto();
        UpdateAuto update = new BuildAuto();

        // Testing createAuto functionality
        create.buildAuto(args[0]);
        create.printAuto("Focus Wagon ZTW");
        System.out.println("Done");

        // Testing updateAuto functionality
        update.updateOptionSetName("Focus Wagon ZTW", "Power Moonroof", "Powered Sunroof");
        update.updateOptionPrice("Focus Wagon ZTW", "Powered Sunroof", "present", 300.00f);
        create.printAuto("Focus Wagon ZTW");

        // Testing AutoException handling with incomplete text file
        create.buildAuto(args[1]);
        create.printAuto("Focus Wagon ZTW");
    }
}
