package com.base.advanced.reflection;

//package step3;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Reflect_stu_4 {

    public static String toString(Object obj) {
        Class cl = obj.getClass();
        String r = "";
        r += "[";

        // 请获取所有 Field 并设置访问权限为 true
        /********** Begin *********/
        Field[] fields = null;
        fields = cl.getDeclaredFields();
//        fields.setAccessible(true);
//        System.out.println(fields);  # 可以遍历出来



        /********** End *********/
        for (Field f : fields) {

//            System.out.println(f);
            f.setAccessible(true);
//            AccessibleObject.setAccessible(fields, true);
            // 此处 if，逻辑为判断 Field 域是否为非静态域
            if (!Modifier.isStatic(f.getModifiers())) {
                if (!r.endsWith("["))
                    r += ",";

                r += f.getName() + "=";

                try {
                    // 请获取域的类型及值
                    /********** Begin *********/

                    Class t = null;
                    Object val = null;

                    t = f.getType();  // 获取类类型
//                    System.out.println(f);
                    val = f.get(obj);
//                    System.out.println(r);
//                    Thread.sleep(1000);


                    /********** End *********/
                    // isPrimitive() 用于判断是否为基本数据类型，若为基础数据类型直接拼接，否则递归调用 toString 方法
                    if (t.isPrimitive())
                        r += val;
                    else
                        r += toString(val);  // 递归时有右中括号
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        r += "]";  // 递归过程和结尾都会调用
        return r;
    }


    public static void main(String[] args) {
        Person_4 person = new Person_4(88, 19, 175);
        System.out.println(toString(person));
//        toString(person);
    }
}

class Person_4 {
    public Integer weight;
    private Integer age;
    private Double height;


    public Person_4(Integer weight, Integer age, double height) {
        this.weight = weight;
        this.age = age;
        this.height = height;
    }
}


