package com.matanalbert.assignment4.driver;

import java.io.IOException;
import com.matanalbert.assignment4.adapter.*;
import com.matanalbert.assignment4.adapter.BuildAuto;
import com.matanalbert.assignment4.adapter.CreateAuto;
import com.matanalbert.assignment4.adapter.UpdateAuto;


/**
 * Sample data files are in data directory :
 * Car-1.txt: A complete automobile definition
 * IncompleteCar.txt: An example of an incomplete automobile that generates exceptions
 */
public class Driver2A {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: Driver2A Car-1.txt IncompleteCar.txt");
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
