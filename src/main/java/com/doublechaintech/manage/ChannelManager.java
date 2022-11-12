package com.doublechaintech.manage;

import com.doublechaintech.realtime.MessageCenterEndPoint;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ChannelManager {
    public static final String __BROADCAST_CHANNEL="_debug";

    public static boolean isDebugChannel(String channelName){
        return channelName.equalsIgnoreCase(__BROADCAST_CHANNEL);
    }

    private static final Logger LOG = Logger.getLogger(ChannelManager.class);


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
        //LOG.info("ensureChanel Channel"  + channelName);
        if(channel!=null){
            //LOG.info("exist Channel"  + channelName);
            return channel;
        }
        LOG.info("new Channel"  + channelName);
        Channel newChannel = new Channel();
        newChannel.setName(channelName);
        getChannelMap().put(channelName,newChannel);
        return newChannel;


    }
    public void switchChannel(String channelName, Audience audience){
        Channel channel=ensureChanel(channelName);
        channel.addAudience(audience);
    }
    public void listenToChannel(String channelName, Audience audience){
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


    public List<String> getEndPointForChannel(String channelName) {
        Channel channel=ensureChanel(channelName);
        return channel.getAudienceList().stream().map(a->a.getName()).collect(Collectors.toList());
    }
}
