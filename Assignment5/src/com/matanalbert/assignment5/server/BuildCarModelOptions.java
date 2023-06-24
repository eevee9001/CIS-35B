package com.matanalbert.assignment5.server;

import com.matanalbert.assignment5.adapter.BuildAuto;
import com.matanalbert.assignment5.model.Automobile;
import com.matanalbert.assignment5.util.FileIO;

import java.util.List;

public class BuildCarModelOptions implements AutoServer {

    private final BuildAuto buildAuto;
    private final FileIO fileIO;

    public BuildCarModelOptions(BuildAuto buildAuto, FileIO fileIO) {
        this.buildAuto = buildAuto;
        this.fileIO = fileIO;
    }

    @Override
    public void addCreatedAutoToLHM(Automobile automobile) {
        buildAuto.addCreatedAutoToLHM(automobile);
    }

    @Override
    public List<String> getModels() {
        return buildAuto.getModels();
    }

    @Override
    public Automobile getAutomobile(String modelName) {
        return buildAuto.getAutomobile(modelName);
    }
}
