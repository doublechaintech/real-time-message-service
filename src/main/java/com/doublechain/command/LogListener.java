package com.doublechain.command;

import com.doublechiantech.service.ChannelService;
import com.doublechiantech.service.MessagePostRequest;
import com.doublechiantech.service.ReceivingService;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public class LogListener implements Runnable {

    public static LogListener withService(ReceivingService receivingService){

        LogListener logListener=new LogListener();
        logListener.setReceivingService(receivingService);
        return logListener;

    }
    private static final Logger LOG = Logger.getLogger(LogListener.class);
    public ReceivingService getReceivingService() {
        return receivingService;
    }

    public void setReceivingService(ReceivingService receivingService) {
        this.receivingService = receivingService;
    }
    private void serveLog() throws IOException {
        DatagramSocket socket = new DatagramSocket(54321);
        while (true){
            byte[] buf = new byte[8192];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            byte[] messageBuf=new byte[packet.getLength()];

            codec(messageBuf,buf);
            MessagePostRequest request=new MessagePostRequest();
            request.setChannelName("_debug");

            CharsetDecoder decoder =
                    StandardCharsets.UTF_8.newDecoder();
            try {
                CharBuffer buffer = decoder.decode(
                        ByteBuffer.wrap(messageBuf));
                request.setMessage(buffer.toString());
                receivingService.postMessage(request);
            } catch (CharacterCodingException ex) {
                LOG.error("Not able to decode the message: "+ex.getMessage());
                LOG.error("The org message: "+new String(buf,0,packet.getLength()));
            }





        }
    }

    private void codec(byte[] messageBuf,byte[] buff) {
        for(int i=0;i<messageBuf.length;i++){
            messageBuf[i]=(byte)(255-buff[i]);
        }
    }

    ReceivingService receivingService;
    @Override
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
