package com.matanalbert.assignment5.driver;

import com.matanalbert.assignment5.adapter.BuildAuto;
import com.matanalbert.assignment5.adapter.CreateAuto;
import com.matanalbert.assignment5.scale.EditAuto;

import java.io.IOException;

public class Driver4 {
    static String modelName = "Focus Wagon ZTW";
    public static void main(String[] args) throws IOException {
        CreateAuto create = new BuildAuto();
        EditAuto edit = new BuildAuto();

        int times = 10000; // large enough number of iterations such that not synced edits may likely crash

        create.buildAuto("New-Car.properties"); // building the automobile
        synchronised(edit, times); // two synchronized methods
        waitForUpdate();
        create.printAuto(modelName);

        create.buildAuto("New-Car.properties"); // rebuild the automobile to test not synced edits
        notSynchronized(edit, times); // two not synchronized methods
        waitForUpdate();
        create.printAuto(modelName);
    }

    private static void synchronised(EditAuto editAuto, int times) {
        System.out.println("================ synchronised =================");
        for (int i = 0; i < times; i++) {
            editAuto.editThread(modelName, 2, new String[] {"Color", "Blue", "Dark Blue"});
            editAuto.editThread(modelName, 3, new String[] {"Color", "Blue", "Red"});
        }
    }

    private static void notSynchronized(EditAuto edit, int times) {
        System.out.println("================ not synchronised =================");
        for (int i = 0; i < times; i++) {
            edit.editThread(modelName, 0, new String[] {"Color", "Blue", "Dark Blue"});
            edit.editThread(modelName, 1, new String[] {"Color", "Blue", "Red"});
        }
    }

    private static void waitForUpdate() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignore
        }
    }

}
