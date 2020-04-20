package com.base.advanced.reflection;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

//package step4;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Reflect_stu {
    public static void main(String[] args) throws InvocationTargetException {
        //使用反射调用
        Class clazz = null;
        try {
            clazz = Class.forName("step4.Apple");
            /********** Begin *********/
            Constructor appleConstructor = clazz.getConstructor();
//            System.out.println(appleConstructor);
            Object appleObject = appleConstructor.newInstance();
            Method setPriceMethod = clazz.getMethod("setPrice", double.class);
            Method setCountMethod = clazz.getMethod("setCount", int.class);
            Method getPriceMethod = clazz.getMethod("getPrice");
            Method getCountMethod = clazz.getMethod("getCount");
            Method getTotalMethod = clazz.getMethod("getTotal", double.class, int.class);

            setPriceMethod.invoke(appleObject, 14);
            System.out.println(getPriceMethod.invoke(appleObject));

            setPriceMethod.invoke(appleObject, 20);
            setCountMethod.invoke(appleObject, 24);

            // 可以通过函数返回值，来获得反射机制调用方法值
            System.out.println(getTotalMethod.invoke(appleObject, getPriceMethod.invoke(appleObject), getCountMethod.invoke(appleObject)));

            /********** End *********/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


class Apple {
    private double price;
    private int count;

    public Apple() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
//        System.out.println(price);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotal(double price, int count) {
        return price * count;
    }
}