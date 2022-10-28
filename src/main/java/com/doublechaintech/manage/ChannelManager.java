package com.doublechaintech.manage;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ApplicationScoped
public class ChannelManager {
    Map<String, Channel> channelMap;

    public Map<String, Channel> getChannelMap() {
        if(channelMap==null){
            channelMap=new ConcurrentHashMap<>();
        }
        return channelMap;
    }

    public void setChannelMap(Map<String, Channel> channelMap) {
        this.channelMap = channelMap;
    }

    public void registerChanel(Channel channel){
        if(getChannelMap().get(channel)!=null){
            throw new IllegalStateException("Channel '"+channel.getName()+"' has already registered");
        }
        getChannelMap().put(channel.getName(),channel);
    }
    public Channel ensureChanel(String channelName){
        Channel channel=getChannelMap().get(channelName);
        if(channel!=null){
            return channel;
        }
        Channel newChannel = new Channel();
        newChannel.setName(channelName);
        getChannelMap().put(channelName,newChannel);
        return newChannel;


    }
    public void switchChannel(String channelName, Audience audience){
        Channel channel=ensureChanel(channelName);
        channel.addAudience(audience);
    }

    public String listSubscriptionText(){

        return getChannelMap().values().stream().map(channel -> {
            return channel.getName()+": "+channel.getAudienceList()
                    .stream().map(a->a.getName())
                    .collect(Collectors.joining(", "));
        }).collect(Collectors.joining("\r\n"));


    }





}
