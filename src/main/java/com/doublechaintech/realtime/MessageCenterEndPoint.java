package com.doublechaintech.realtime;
import com.doublechiantech.service.ChannelService;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Map;
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


    Map<String, Session> sessions = new ConcurrentHashMap<>();
    private static final Logger LOG = Logger.getLogger(ChannelService.class);
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        sessions.put(username, session);
        broadcast(username+" is here");
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessions.remove(username);
        broadcast("User " + username + " left");

    }

    @OnError
    public void onError(Session session, @PathParam("username") String username, Throwable throwable) {
        sessions.remove(username);
        broadcast("User " + username + " left on error: " + throwable);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("username") String username) {
        if (message.equalsIgnoreCase("_ready_")) {
            broadcast("User " + username + " joined");
        } else {
            broadcast(">> " + username + ": " + message);
        }
    }

    public synchronized void  multicast(List<String> endPoints, Object message){


        sessions.entrySet().stream()
                .filter(entry->endPoints.contains(entry.getKey()))
                .map(entry -> {
                    LOG.info("sending message to "+ entry.getKey());
                    return entry;
                })
                .map(entry -> entry.getValue()).forEach(session -> {

                    session.getAsyncRemote().sendText(message.toString(), result ->  {
                        if (result.getException() != null) {
                            LOG.error("Unable to send message: " + result.getException());
                        }
                    });
                });
    }

    private void broadcast(String message) {
        sessions.values().forEach(s -> {

            s.getAsyncRemote().sendText(message, result ->  {
                if (result.getException() != null) {
                    LOG.error("Unable to send message: " + result.getException());
                }
            });
        });
    }
}
