package com.doublechiantech.service;
import com.doublechaintech.manage.Audience;
import com.doublechaintech.manage.Channel;
import com.doublechaintech.manage.ChannelManager;
import com.doublechaintech.realtime.MessageCenterEndPoint;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;
@Path("/message-center/post")
@ApplicationScoped
public class ReceivingService {
    private static final Logger LOG = Logger.getLogger(ReceivingService.class);
    @Inject
    MessageCenterEndPoint messageCenterEndPoint;

    @Inject
    ChannelManager channelManager;
    @PUT
    public MessagePostResponse postMessage(MessagePostRequest request) {
        try{

            checkMessagePostRequest(request);
        }catch (IllegalArgumentException ex){
            return MessagePostResponse.withMessage(ex.getMessage());
        }



        if(messageCenterEndPoint==null){
            LOG.error("container is not working.."+ messageCenterEndPoint);
            return new MessagePostResponse();
        }

        List<String> endPoints = channelManager.getEndPointForChannel(request.getChannelName());
        LOG.error(channelManager.listSubscriptionText());
        messageCenterEndPoint.multicast(endPoints,request.getMessage());
        return new MessagePostResponse();
    }

    private void checkMessagePostRequest(MessagePostRequest request) {

        if(request==null){
            throw new IllegalArgumentException("request is null");
        }
        if(request.getChannelName()==null){
            throw new IllegalArgumentException("chanelName is null");
        }
        if(request.getMessage()==null){
            throw new IllegalArgumentException("message is null");
        }

    }


}
