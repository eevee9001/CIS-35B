package com.matanalbert.assignment5.model;

public interface AutoRequest {
    enum RequestType {
        ADD_AUTO,
    }

    RequestType getType();
}
