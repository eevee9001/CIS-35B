package com.matanalbert.assignment5.model;

import java.io.Serializable;
import java.util.Properties;

import static com.matanalbert.assignment5.model.AutoRequest.RequestType.ADD_AUTO;

public class AddAutoRequest implements AutoRequest, Serializable {
    private final Properties properties;

    public AddAutoRequest(Properties properties) {
        this.properties = properties;
    }

    @Override
    public RequestType getType() {
        return ADD_AUTO;
    }

    public Properties getProperties() {
        return properties;
    }
}
