package com.matanalbert.assignment3.driver;

import com.matanalbert.assignment3.adapter.*;

import java.io.IOException;

public class Driver3 {
    public static void main(String[] args) throws IOException {
        if (args.length <= 0) {
            System.out.println("Usage: Driver3 file... ");
            System.exit(1);
        }
        CreateAuto create = new BuildAuto();
        UpdateAuto update = new BuildAuto();

        for (String file : args) {
            create.buildAuto(file);
        }
    }
}
