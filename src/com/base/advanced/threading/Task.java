package com.base.advanced.threading;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

// package com.threading;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

//package step4;

// import jdk.nashorn.internal.ir.IfNode;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task {
    public volatile int inc = 0;
    public Lock lock = new ReentrantLock();
    public void increase() throws InterruptedException {
        if (lock.tryLock(100, TimeUnit.SECONDS))
            try {
                inc++;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
    }
    public static void main(String[] args) {
        final Task test = new Task();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++) {
                        try {
                            test.increase();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
            }.start();
        }
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();  // 不同电脑性能，所体现出来的效果不同
        System.out.println(test.inc);
    }
}
