package com.doublechiantech.service;

import java.util.Date;

public class AddChanelResponse {

    private String message="yes"+new Date().getTime();

    public static AddChanelResponse withMessage(String message) {

        AddChanelResponse addChanelResponse=new AddChanelResponse();
        addChanelResponse.setMessage(message);
        return addChanelResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
