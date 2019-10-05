package com.cheirmin.service;

public class Test4 {
    public static void main(String[] args) {
        new Thread(()->{
            Test3.sender();
        }).start();
    }
}
