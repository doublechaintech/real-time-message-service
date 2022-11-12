package com.doublechain.command;

import com.doublechiantech.service.MessagePostRequest;
import com.doublechiantech.service.ReceivingService;

public class CounterTask implements Runnable {
    public ReceivingService getReceivingService() {
        return receivingService;
    }

    public void setReceivingService(ReceivingService receivingService) {
        this.receivingService = receivingService;
    }
    
    ReceivingService receivingService;
    @Override
    public  void run(){
        int i=0;
        while (true){
            i++;
            //System.out.println("count at "+service);
            try{
                MessagePostRequest r=new MessagePostRequest();
                r.setChannelName("_debug");
                r.setMessage("count to "+ i);
                receivingService.postMessage(r);
                Thread.sleep(1000);
            }catch (Exception e){

            }

        }

    }
}
