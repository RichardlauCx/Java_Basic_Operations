package com.base.advanced.threading;

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

public class Volatile {
    public volatile int inc = 0;
    public Lock lock = new ReentrantLock();
//请在此添加实现代码
    /********** Begin **********/

    public void increase() throws InterruptedException {
//        lock.lock();
        if (lock.tryLock(100, TimeUnit.SECONDS)) {
            // 上锁就很安全（那一瞬间）
            try {

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                System.out.println(inc);
                lock.unlock();
            }
        }
//        System.out.println(inc);
        inc++;
    }


    /********** End **********/

    public static void main(String[] args) throws InterruptedException {
        final Volatile test = new Volatile();

        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        try {
                            test.increase();
                        } catch (InterruptedException e) {
                            // 有抛出就要有捕获
                            e.printStackTrace();
                        }
                    }
                };
            }.start();
            //
//            Thread.currentThread().join();
        }

//        while(Thread.activeCount()>1)  //保证前面的线程都执行完
//            Thread.yield(); // 不同电脑性能，所体现出来的效果不同
        Thread.sleep(100);  // 等待子进程全部结束后
        System.out.println(test.inc);
    }
}

