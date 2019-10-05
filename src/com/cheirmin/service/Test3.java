package com.cheirmin.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        new Thread(()->{
            sender();
        }).start();
        new Thread(()->{
            recever();
        }).start();
    }

    public static void sender(){
        try {
            Scanner sc = new Scanner(System.in);
            DatagramSocket socket = new DatagramSocket();
            //ip
            InetAddress ip = InetAddress.getLocalHost();
            //传输语句
            int i=0;
            while (true){
                String str = new String();
                str = sc.next();
                byte[] data = str.getBytes();
                DatagramPacket  dp = new DatagramPacket(data,data.length,ip,9090);
                socket.send(dp);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void recever(){
        try {
            DatagramSocket socket = new DatagramSocket(9090);

            byte[] b = new byte[1024*64];

            DatagramPacket dp = new DatagramPacket(b,b.length);

            System.out.println("数据接收中");

            while (true){
                //开启接收
                socket.receive(dp);

                byte[] data = Arrays.copyOf(b,dp.getLength());
                String str = new String(data);
                System.out.println("长度"+dp.getLength()+"  数据："+str);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
