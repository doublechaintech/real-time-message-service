package com.doublechain.command;

import com.doublechiantech.service.ChannelService;
import com.doublechiantech.service.MessagePostRequest;
import com.doublechiantech.service.ReceivingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public class MulticastListener implements Runnable{
    public static MulticastListener withService(ReceivingService receivingService){

        MulticastListener multicastListener=new MulticastListener();
        multicastListener.setReceivingService(receivingService);
        return multicastListener;

    }
    private static final Logger LOG = Logger.getLogger(MulticastListener.class);

    ReceivingService receivingService;

    public ReceivingService getReceivingService() {
        return receivingService;
    }

    public void setReceivingService(ReceivingService receivingService) {
        this.receivingService = receivingService;
    }
/*
* DatagramPacket datagramPacket =
          new DatagramPacket(bytes, 0, bytes.length, InetAddress.getByName("224.0.0.8"), 6789);
      MulticastSocket multicastSocket = new MulticastSocket();
      multicastSocket.send(datagramPacket);
*
* */
    private ObjectMapper mapper(){
        return new ObjectMapper();
    }
    private void serveLog() throws IOException {
        int port=6789;
        String address="224.0.0.8";
        MulticastSocket socket=new MulticastSocket(port);
        InetAddress group= InetAddress.getByName(address);
        socket.joinGroup(group);
        LOG.info("Joined the socket "+address+":"+port);
        while (true){
            byte[] buf = new byte[8192];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            LOG.info("Before recv "+address+":"+port);
            socket.receive(packet);
            LOG.info("After recv "+address+":"+port);
            byte[] messageBuf=new byte[packet.getLength()];
            System.arraycopy(buf,0,messageBuf,0,packet.getLength());
            CharsetDecoder decoder =
                    StandardCharsets.UTF_8.newDecoder();
            try {
                CharBuffer buffer = decoder.decode(
                        ByteBuffer.wrap(messageBuf));
                String message=buffer.toString();
                LOG.info("Receiving message: "+message);
                MessagePostRequest request= mapper().readValue(message,MessagePostRequest.class);
                receivingService.postMessage(request);
            } catch (CharacterCodingException ex) {
                LOG.error("Not able to decode the message: "+ex.getMessage());
                LOG.error("The org message: "+new String(buf,0,packet.getLength()));
            }

        }

    }


    public  void run(){
        if(receivingService==null){
            LOG.error("receivingService is not set, exit.");
            return;
        }
        try{
            serveLog();
        }catch (Exception e){
            LOG.error("Exception catched." + e.getMessage());
        }


    }
}
