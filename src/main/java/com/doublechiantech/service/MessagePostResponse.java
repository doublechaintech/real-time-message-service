package com.doublechiantech.service;

import java.util.Date;

public class MessagePostResponse {
    private String message="yes"+new Date().getTime();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
