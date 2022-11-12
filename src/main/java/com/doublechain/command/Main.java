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
