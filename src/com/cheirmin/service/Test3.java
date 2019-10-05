package com.cheirmin.service;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

/**
 * 模拟服务器端，接收192.168.1.104的数据
 * 发送的时候需要获取客户端传来的数据
 */
public class Test3 {
    public static String[] strings;
    public static void main(String[] args) {
        recever();
    }

    public static void sender(String sendIP,String targetIP,String str){
        try {
            //socket
            DatagramSocket socket = new DatagramSocket();
            //ip
            InetAddress ip = InetAddress.getByName(targetIP);
            //拼接 显示消息来源
            str = sendIP.concat(";").concat(targetIP).concat(";").concat(str);
            //传输语句
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

            System.out.println("服务器启动成功==");

            while (true){
                //开启接收
                socket.receive(dp);
                byte[] data = Arrays.copyOf(b,dp.getLength());
                String str = new String(data);

                //回复消息 数据解析
                strings= str.split(";");

                sender(strings[0],strings[1],strings[2]);
                System.out.println("新消息解析成功，已转发--"+str);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}