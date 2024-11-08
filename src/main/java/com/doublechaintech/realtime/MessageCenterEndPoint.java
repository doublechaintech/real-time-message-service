package com.doublechaintech.realtime;
import com.doublechiantech.service.ChannelService;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.common.util.DateUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
@ServerEndpoint("/message-center/{username}")
@ApplicationScoped

public class MessageCenterEndPoint {
    /*
    static MessageCenterEndPoint messageCenterEndPoint;
    public static synchronized  MessageCenterEndPoint inst(){
        if(messageCenterEndPoint==null){
            messageCenterEndPoint=new MessageCenterEndPoint();
        }
        return messageCenterEndPoint;

    }*/


    public static  String PUBLIC_CHANNEL="public";

    Map<String, Session> sessions = new ConcurrentHashMap<>();
    private static final Logger LOG = Logger.getLogger(ChannelService.class);


    public String getSessionId(String username,Session session){
        if(PUBLIC_CHANNEL.equals(username)){
            return "public:"+session.getId();
        }
        return username;
    }
    public String getMaskedId(String username,Session session){
        if(PUBLIC_CHANNEL.equals(username)){
            return "public:"+session.getId();
        }
        if(username.length() < 4){
            return "*****";
        }
        return "*****";
    }


    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        String internalId=username;

        sessions.put(getSessionId(username,session), session);
        broadcast(getMaskedId(username,session)+" joined at " + DateUtil.formatDate(new Date(),"YYYY-MM-DD hh:mm:ss.SSS"));
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessions.remove(getSessionId(username,session));
        broadcast("User " + getMaskedId(username,session) + " left");

    }

    @OnError
    public void onError(Session session, @PathParam("username") String username, Throwable throwable) {
        sessions.remove(getSessionId(username,session));
        broadcast("User " + getMaskedId(username,session) + " left on error: " + throwable);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("username") String username) {

    }

    public synchronized void  multicast(List<String> endPoints, Object message){


        sessions.entrySet().stream()
                .filter(entry->endPoints.contains(entry.getKey()))
                .map(entry -> {
                    LOG.info("sending message to "+ entry.getKey());
                    return entry;
                })
                .map(entry -> entry.getValue()).forEach(session -> {
                    if(!session.isOpen()){
                        return;//not clean here, fine for now
                    }
                    session.getAsyncRemote().sendText(message.toString(), result ->  {
                        if (result.getException() != null) {
                            LOG.error("Unable to send message: " + result.getException());
                        }
                    });
                });
    }

    public void broadcast(String message) {
        sessions.values().forEach(s -> {

            s.getAsyncRemote().sendText(message, result ->  {
                if (result.getException() != null) {
                    LOG.error("Unable to send message: " + result.getException());
                }
            });
        });
    }
}
