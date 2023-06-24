package com.matanalbert.assignment5.model;

import java.io.Serializable;

public class AddAutoResponse implements AutoResponse, Serializable {
    private final String status;

    public AddAutoResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
