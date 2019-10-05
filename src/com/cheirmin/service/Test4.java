package com.cheirmin.service;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 模拟客户端
 * 发送目标192.168.1.104
 * 接收端为自己的ip 和端口号
 */

public class Test4 {
    public static InetAddress localIp = null;
    public static void main(String[] args) {
        new Thread(()->{
            sender("192.168.1.104","哈哈，是我");
        }).start();
        new Thread(()->{
//            recever();
        }).start();
    }

    public static void sender(String targetIP,String str){
        try {
            //socket
            DatagramSocket socket = new DatagramSocket();
            //ip
            InetAddress ip = InetAddress.getByName("192.168.1.104");

            //客户端ip
            String clientIP = InetAddress.getLocalHost().getHostAddress();

            //拼接ip+数据
            str = clientIP.concat(";").concat(targetIP).concat(";").concat(str);
            System.out.println("数据="+str);

            byte[] data = str.getBytes();
            DatagramPacket  dp = new DatagramPacket(data,data.length,ip,9090);
            socket.send(dp);
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

            System.out.println("客户端数据接收中");

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