package com.base.advanced.threading;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

public class Supervene {
    public  int inc = 0;
    public void increase() {
        inc++;
    }
    public static void main(String[] args) {
        final Supervene test = new Supervene();
//        System.out.println(test);

        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
//                        System.out.println(test.inc);
                };
            }.start();
        }
        while(Thread.activeCount()>1)  //  保证前面的线程都执行完  ---> activeCount()方法返回当前激活线程数
//            System.out.println(test.inc);
            Thread.yield();
        // 由于没有保证原子性、可见性，以及有序性。所以一部分还未来得及写入主存，就被另外的线程拿来直接用啦
        System.out.println(test.inc);
    }
}
