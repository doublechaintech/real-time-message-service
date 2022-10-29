package com.doublechiantech.service;
import com.doublechaintech.manage.Audience;
import com.doublechaintech.manage.Channel;
import com.doublechaintech.manage.ChannelManager;
import com.doublechaintech.realtime.MessageCenterEndPoint;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;
@Path("/post")
@ApplicationScoped
public class ReceivingService {
    @Inject
    MessageCenterEndPoint messageCenterEndPoint;

    @Inject
    ChannelManager channelManager;
    @PUT
    public MessagePostResponse postMessage(MessagePostRequest request) {

        if(messageCenterEndPoint==null){
            System.out.println("container is not working.."+ messageCenterEndPoint);
            return new MessagePostResponse();
        }

        List<String> endPoints = channelManager.getEndPointForChannel(request.getChannelName());
        System.out.println(channelManager.listSubscriptionText());

        messageCenterEndPoint.multicast(endPoints,request.getMessage());
        return new MessagePostResponse();
    }


}
