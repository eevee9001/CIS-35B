package com.matanalbert.assignment5.model;

import java.io.Serializable;

import static com.matanalbert.assignment5.model.AutoRequest.RequestType.DOWNLOAD_AUTO;

public class DownloadAutoRequest implements AutoRequest, Serializable {
    private final String modelName;

    public DownloadAutoRequest(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public RequestType getType() {
        return DOWNLOAD_AUTO;
    }

    public String getModelName() {
        return modelName;
    }
}
