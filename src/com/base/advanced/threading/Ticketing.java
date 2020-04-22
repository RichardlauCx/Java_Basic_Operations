package com.base.advanced.threading;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

//package step2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********* Begin *********/
//定义站台类，实现卖票的功能。
public class Ticketing {

    public static void main(String[] args) throws InterruptedException {
        Station sta1 = new Station();
        Station sta2 = new Station();
        Station sta3 = new Station();

        sta1.start();
//        Thread.sleep(100);
        sta2.start();
//        Thread.sleep(100);
        sta3.start();

//        sta1.join();
//        sta2.join();
//        sta3.join();

//        while (Thread.activeCount() > 1)
//            Thread.yield();

    }

}

//定义站台类，实现卖票的功能。
class Station extends Thread{
    static int vote = 20;  // 保证票数的一致，使用静态方法
    static Object ob  = new Object();  // 创建一个静态锁对象， 值是任意的

    public void ticket(){
        System.out.println("卖出了第" + vote + "张票");
        vote--;
    }

    @Override
    public void run() {
        while (vote > 0) {
            synchronized (ob) {
                // 增加互斥锁
                if (vote > 0) {
                    // 线程过程可能会出现数据上面很多问题，所以保险一点儿
                    ticket();
                }
            }

            if (vote == 0) {
                System.out.println("票卖完了");
            }

            try {
                Thread.sleep(200);  // 相当于给每个线程，每次执行一些缓冲时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
}

//定义站台类，实现卖票的功能。
class Station_2 extends Thread{  // TODO 有机会研究一下通过lock方法同步访问变量
    static volatile int vote = 20;  // 保证票数的一致，使用静态方法
    static Object ob  = new Object();  // 创建一个静态锁对象， 值是任意的
    Lock lock = new ReentrantLock();

    public void ticket(){
//        lock.lock();
        if (lock.tryLock()) {
            try {
                vote--;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            System.out.println("卖出了第" + vote + "张票");

        }
    }

    @Override
    public void run() {
        while (vote > 0) {
//            synchronized (ob) {
                if (vote > 0) {
                    // 线程过程可能会出现数据上面很多问题，所以保险一点儿
                    ticket();
                }
//            }

            if (vote == 0) {
                System.out.println("票卖完了");
            }

            try {
                Thread.sleep(200);  // 相当于给每个线程，每次执行一些缓冲时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.unlock();
        }
    }
}

class Station_3 extends Thread {
    private Lock lock = new ReentrantLock();

    private volatile int votes = 20;


    public void ticket() {
        System.out.println("卖出了第" + votes + "张票");
        votes--;
    }

    @Override
    public void run() {
        while (votes > 0) {
            try {
                if (lock.tryLock(100, TimeUnit.SECONDS))
                    // 如果当前有线程在执行，则其余前程先不可执行
//                lock.lock();
                    if (votes > 0)
                {
                    ticket();
                }

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


            if (votes == 0) {
                System.out.println("票卖完了");
            }


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        if (votes == -2) {
//        System.out.println("票卖完了");
//        System.exit(0);
//        votes--;
    }}
}
/********* End *********/

