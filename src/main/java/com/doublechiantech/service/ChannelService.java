package com.doublechiantech.service;

import com.doublechaintech.manage.Audience;
import com.doublechaintech.manage.ChannelManager;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
@ApplicationScoped
@Path("/message-center/channel")
public class ChannelService {
    private static final Logger LOG = Logger.getLogger(ChannelService.class);
    @Inject
    ChannelManager channelManager;

    @PUT
    public AddChanelResponse addToChanel(AddToChannelRequest request) {

        try{
            checkAddChangelRequest(request);
        }catch (IllegalArgumentException ex){
            LOG.error(ex.getMessage());
            return AddChanelResponse.withErrorMessage(ex.getMessage());
        }


        if(channelManager==null){
            String msg="the container is not working for ChannelService";
            LOG.info(msg);
            return AddChanelResponse.withErrorMessage(msg);
        }
        //ChannelManager channelManager = ChannelManager.inst();
        channelManager.listenToChannel(request.getChannelName(), Audience.withName(request.getEndpoint()));
        return AddChanelResponse.withMessage("End point '"+request.getChannelName()+"' has been added to channel '"+request.getEndpoint()+"'.");
    }

    private void checkAddChangelRequest(AddToChannelRequest request) {
        if(request==null){
            throw new IllegalArgumentException("request is null");
        }
        if(request.getChannelName()==null){
            throw new IllegalArgumentException("chanelName is null");
        }
        if(request.getChannelName().length()<1){
            throw new IllegalArgumentException("chanelName should not be an empty string");
        }
        if(request.getEndpoint()==null){
            throw new IllegalArgumentException("endpoint is null");
        }
        if(request.getEndpoint().length() < 1 ){
            throw new IllegalArgumentException("endpoint should not be an empty string");
        }



    }

}
