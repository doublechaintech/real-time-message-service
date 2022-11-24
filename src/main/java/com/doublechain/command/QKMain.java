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

        //new Thread(LogListener.withService(service)).start();
        new Thread(MulticastListener.withService(service)).start();
        //new Thread(MulticastSender.inst()).start();
        Quarkus.waitForExit();
        //System.out.println("find the service: " +service );
        return 0; //exit code
    }

}

/*
*
*
*
package com.doublechain.command;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;


public class Main {

    public static void main(String... args) {
        System.out.println("Hello World");

        new Thread(){
            int i=0;
            public  void run(){
                while (true){
                    i++;
                    System.out.println("count at "+i);
                    try{
                        Thread.sleep(5000);
                    }catch (Exception e){

                    }

                }

            }
        }.start();

        //Quarkus.run(HelloWorldMain.class, args);
        Quarkus.run(args);
    }
}

*
*
* */