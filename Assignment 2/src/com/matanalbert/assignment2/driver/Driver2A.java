package com.matanalbert.assignment2.driver;

import com.matanalbert.assignment2.adapter.BuildAuto;
import com.matanalbert.assignment2.adapter.CreateAuto;
import com.matanalbert.assignment2.adapter.ProxyAutomobile;
import com.matanalbert.assignment2.adapter.UpdateAuto;

import java.io.IOException;

public class Driver2A {
    public static void main(String[] args) throws IOException {
        CreateAuto create = new BuildAuto();
        UpdateAuto update = new BuildAuto();
        create.buildAuto("Assignment 2/data/Car-1.txt");
        create.printAuto("Focus Wagon ZTW");
        System.out.println("Done");

        update.updateOptionSetName("Focus Wagon ZTW", "Power Moonroof", "Powered Sunroof");
        update.updateOptionPrice("Focus Wagon ZTW", "Powered Sunroof", "present", 300.00f);
        create.printAuto("Focus Wagon ZTW");
    }
}
