package com.matanalbert.assignment5.server;

import com.matanalbert.assignment5.model.Automobile;

import java.util.Properties;

public class BuildCarModelOptions implements AutoServer {

    public BuildCarModelOptions() {

    }

    public Automobile createAutoFromClient(Properties properties) {
        // placeholder
        String make = null;
        String model = null;
        int year = 0;
        float basePrice = 0;
        Automobile automobile = new Automobile(make, model, year, basePrice);
        return automobile;
    }


    public void addCarToLHM(Automobile automobile) {
        Properties properties = new Properties();
        addCreatedAutoToLHM(createAutoFromClient(properties));
    }

    @Override
    public void addCreatedAutoToLHM(Automobile automobile) {

    }
}
