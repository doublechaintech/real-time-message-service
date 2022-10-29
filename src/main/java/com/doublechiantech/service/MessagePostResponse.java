package com.doublechiantech.service;

import java.util.Date;

public class MessagePostResponse {

    public static MessagePostResponse withMessage(String message){
        MessagePostResponse messagePostResponse=new MessagePostResponse();
        messagePostResponse.setMessage(message);
        return  messagePostResponse;
    }
    private String message="yes"+new Date().getTime();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
