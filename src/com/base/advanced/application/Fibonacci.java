package com.base.advanced.application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Fibonacci {
//    private int n;
    public static void main(String[] args) {
//        n = -1;
        System.out.println(Arrays.toString(args));
        Scanner in = new Scanner(System.in);
        try {
            int n = in.nextInt();
            int fibonacci = calc(n);
            System.out.println("最终结果为：" + fibonacci);
        } catch (InputMismatchException e) {
            System.out.println("请确认您是否按照要求，输入了一个整数，若不是请重试");
            main(args);
        }



    }

    public static int calc(int n) {
//        System.out.println("success!");

        if (n < 0)
            return -1;

        else if (n == 0) {
            return 0;
        }

        else if (n == 1 || n ==2) {
            return 1;
        }

        else {
            return calc(n-1) + calc(n-2);
        }
    }
}
