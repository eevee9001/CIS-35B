package com.matanalbert.assignment5.server;

import com.matanalbert.assignment5.model.Automobile;

import java.util.List;

public interface AutoServer {
    public void addCreatedAutoToLHM(Automobile automobile);

    public List<String> getModels();
}
