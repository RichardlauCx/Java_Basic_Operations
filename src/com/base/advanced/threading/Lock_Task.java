package com.base.advanced.threading;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

//package step3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock_Task {

    public static void main(String[] args) {
        final Insert insert = new Insert();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    insert.insert(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    insert.insert(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                try {
                    insert.insert(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 设置线程优先级
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        t3.start();

    }
}

class Insert {

    public static int num;
    public Lock lock = new ReentrantLock();
    // 在这里定义Lock

    public void insert(Thread thread) throws InterruptedException {
        /********* Begin *********/

//        lock.lock();
//        TimeUnit tu = new TimeUnit();
        if (lock.tryLock(100, TimeUnit.SECONDS)) {

            try {
                System.out.println(thread.getName() + "得到了锁");
                for (int i = 0; i < 5; i++) {
                    num++;
                    System.out.println(num);
                }

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                lock.unlock();
                System.out.println(thread.getName() + "释放了锁");
            }

        }

        else {
            System.out.println(thread.getName() + "获取锁失败");
        }



        /********* End *********/
    }

}

