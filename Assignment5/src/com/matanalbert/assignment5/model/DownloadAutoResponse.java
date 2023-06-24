package com.matanalbert.assignment5.model;

import java.io.Serializable;

public class DownloadAutoResponse implements AutoResponse, Serializable {
    private final Automobile automobile;

    public DownloadAutoResponse(Automobile automobile) {
        this.automobile = automobile;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

}
