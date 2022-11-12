package com.doublechain.command;

import com.doublechiantech.service.MessagePostRequest;
import com.doublechiantech.service.ReceivingService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.inject.Inject;

@QuarkusMain

public class QKMain implements QuarkusApplication {

    @Inject
    ReceivingService service;

    @Override
    public int run(String... args) throws Exception {
        System.out.println("Hello World " +service );

        new Thread(){

            public  void run(){
                int i=0;
                while (true){
                    i++;
                    //System.out.println("count at "+service);
                    try{
                        MessagePostRequest r=new MessagePostRequest();
                        r.setChannelName("_debug");
                        r.setMessage("count to "+ i);
                        service.postMessage(r);
                        Thread.sleep(1000);
                    }catch (Exception e){

                    }

                }

            }
        }.start();
        Quarkus.waitForExit();
        //System.out.println("find the service: " +service );
        return 42; //exit code
    }

}