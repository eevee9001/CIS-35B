package com.matanalbert.assignment5.model;

import java.io.Serializable;
import java.util.List;

public class GetAutoListResponse implements AutoResponse, Serializable {
    private final List<String> models;

    public GetAutoListResponse(List<String> models) {
        this.models = models;
    }

    public List<String> getModels() {
        return models;
    }
}
