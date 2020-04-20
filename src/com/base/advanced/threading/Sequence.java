package com.base.advanced.threading;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

//package step1;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sequence {
    public static void main(String[] args) throws Exception {
        /********* Begin *********/
        //在这里创建线程， 开启线程
        Object obj_1 = new Object();
        Object obj_2 = new Object();
        Object obj_3 = new Object();

        MyThread t1 = new MyThread(String.valueOf('A'), obj_1, obj_2);
        MyThread t2 = new MyThread(String.valueOf('B'), obj_2, obj_3);
        MyThread t3 = new MyThread(String.valueOf('C'), obj_3, obj_1);

        t1.setPriority(10);  //  并没有按照预设的优先级进行 ---> 要保证线程是按顺序启动的
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(1);

        t1.start();
        Thread.sleep(100);  // 保证执行的先后顺序
        t2.start();
        Thread.sleep(100);
        t3.start();

        t1.join();
        t2.join();
        t3.join();  //  join阻塞无法结束，是个问题，Python中也遇到啦 ---> 子线程一直没有让其结束，结束后方可出join函数
//        Thread.join
//        Thread.sleep(100);


        /********* End *********/
    }
}

class MyThread extends Thread {
    /********* Begin *********/


    List<String> threadName;
    Object obj1;
    Object obj2;


    public MyThread(String threadName, Object obj1, Object obj2) {

        this.obj1 = obj1;
        this.obj2 = obj2;
        this.threadName = Collections.singletonList(String.join("", Collections.nCopies(2, threadName)));
        // 包装类实现重复, singletonList：单元素不可变的集合
    }


    public void run() {
//        Lock lock = new ReentrantLock();

        int count = 5;

        while(count > 0){
            synchronized (obj1) {
                synchronized (obj2) {


                    System.out.println("Java Thread" + this.threadName.get(0));
                    count--;
                    obj2.notify();}  // 先执行完操作，再唤醒下一个等待中的线程

                try {
                    obj1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }




        }
        System.exit(0);  // 线程结束后退出

        }

//        try {
//            while(count > 0){
//                if (lock.tryLock(100, TimeUnit.SECONDS))
//                {
//
//
//                    System.out.println("Java Thread" + this.threadName.get(0));
//                    count--;
//                }
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }

    }

    /********* End *********/

