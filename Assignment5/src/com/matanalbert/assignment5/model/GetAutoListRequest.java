package com.matanalbert.assignment5.model;

import java.io.Serializable;

import static com.matanalbert.assignment5.model.AutoRequest.RequestType.GET_AUTO_LIST;

public class GetAutoListRequest implements AutoRequest, Serializable {


    @Override
    public RequestType getType() {
        return GET_AUTO_LIST;
    }
}
