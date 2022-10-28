package com.doublechiantech.service;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;
@Path("/post")
@ApplicationScoped
public class ReceivingService {

    @PUT
    public MessagePostResponse postMessage(MessagePostRequest request) {
        return new MessagePostResponse();
    }

}
