package com.doublechiantech.service;

import java.util.Date;

public class AddChanelResponse {

    private String message="yes";

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    private int resultCode=0;

    public static AddChanelResponse withMessage(String message) {

        AddChanelResponse addChanelResponse=new AddChanelResponse();
        addChanelResponse.setMessage(message);
        return addChanelResponse;
    }
    public static AddChanelResponse withErrorMessage(String message) {

        AddChanelResponse addChanelResponse=new AddChanelResponse();
        addChanelResponse.setResultCode(1);
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
