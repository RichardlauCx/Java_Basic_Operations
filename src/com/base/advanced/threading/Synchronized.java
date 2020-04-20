package com.base.advanced.threading;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

//package step2;

public class Synchronized {

    public static void main(String[] args) {

        final insertData insert = new insertData();

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    insert.insert(Thread.currentThread());
                }
            }).start();
        }

    }
}

class insertData{

    public static int num =0;

    /********* Begin *********/
    public void insert(Thread thread){

        for (int i = 0; i <= 5; i++) {
            synchronized (this) {
                // 针对代码块做修饰
                num++;
                System.out.println(num);
            }

        }
    }

    /********* End *********/
}
