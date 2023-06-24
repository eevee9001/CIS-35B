package com.matanalbert.assignment5.model;

public interface AutoRequest {
    enum RequestType {
        ADD_AUTO,
        GET_AUTO_LIST,
        DOWNLOAD_AUTO,
    }

    RequestType getType();
}
