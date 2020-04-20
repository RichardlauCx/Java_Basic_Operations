package com.base.advanced.threading;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

public class PintCharStan implements Runnable {
    private String name;
    private Object prev;
    private Object self;
    private PintCharStan(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }
    public void run() {
        int count = 5;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);
                    count--;
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.exit(0);//退出jvm
    }
    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        PintCharStan ta = new PintCharStan("A", b, a);
        PintCharStan tb = new PintCharStan("B", a, b);
        new Thread(ta).start();
        Thread.sleep(100);  //确保按顺序A、B执行
        new Thread(tb).start();
        Thread.sleep(100);
    }
}
