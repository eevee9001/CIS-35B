package com.matanalbert.assignment5.server;

import com.matanalbert.assignment5.adapter.BuildAuto;
import com.matanalbert.assignment5.model.Automobile;
import com.matanalbert.assignment5.util.FileIO;

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
}
