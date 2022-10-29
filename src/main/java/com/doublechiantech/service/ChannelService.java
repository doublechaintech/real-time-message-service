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
        if(channelManager==null){
            LOG.info("the container is not working for ChannelService");
            return new AddChanelResponse();
        }
        //ChannelManager channelManager = ChannelManager.inst();
        channelManager.listenToChannel(request.getChannelName(), Audience.withName(request.getEndpoint()));
        return new AddChanelResponse();
    }

}
