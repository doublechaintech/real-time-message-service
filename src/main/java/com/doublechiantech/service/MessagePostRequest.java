package com.doublechiantech.service;

import java.util.ArrayList;
import java.util.List;

public class MessagePostRequest {
    private String channelName;
    private String message;



    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getSubscribes() {
        if(subscribes==null){
            subscribes = new ArrayList<>();
        }
        return subscribes;
    }

    public void setSubscribes(List<String> subscribes) {
        this.subscribes = subscribes;
    }

    List<String> subscribes;
}
