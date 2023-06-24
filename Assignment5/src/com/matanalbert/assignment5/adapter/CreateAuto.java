package com.matanalbert.assignment5.adapter;

import java.io.IOException;

public interface CreateAuto {

    enum FileType {
        TEXT,
        PROPERTIES,
    }

    public void buildAuto(String filename, FileType fileType) throws IOException;
    public void printAuto(String modelName);
}
