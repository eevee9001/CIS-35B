package com.matanalbert.assignment5.server;

import com.matanalbert.assignment5.model.Automobile;

import java.util.List;

public interface AutoServer {
    void addCreatedAutoToLHM(Automobile automobile);

    List<String> getModels();

    Automobile getAutomobile(String modelName);
}
