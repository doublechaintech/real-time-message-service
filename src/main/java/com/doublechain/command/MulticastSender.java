package com.doublechain.command;


import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSender implements Runnable {


    public static MulticastSender inst(){
        return new MulticastSender();
    }

    @Override
    public  void run(){
        int i=0;
        while (true){
            i++;
            //System.out.println("count at "+service);
            try{
                byte [] bytes="hello".getBytes();
                DatagramPacket datagramPacket =
                        new DatagramPacket(bytes, 0, bytes.length, InetAddress.getByName("224.0.0.8"), 6789);
                MulticastSocket multicastSocket = new MulticastSocket();
                multicastSocket.send(datagramPacket);
                Thread.sleep(1000);
            }catch (Exception e){

            }

        }

    }

    public static void main(String[] args) throws Exception{
        byte [] bytes="hello".getBytes();
        DatagramPacket datagramPacket =
                new DatagramPacket(bytes, 0, bytes.length, InetAddress.getByName("224.0.0.8"), 6789);
        MulticastSocket multicastSocket = new MulticastSocket();
        multicastSocket.send(datagramPacket);
    }
}
