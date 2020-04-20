package com.base.advanced.threading;

//package step3;

public class PrintChar implements Runnable {
//    private char chara;
    private String chara;
    private Object self;
    private Object object;
    private Object prove;
//    private static String string ;
//    private String string ;
    //请在此添加实现代码

    public PrintChar(String chara, Object self, Object prove) {
        this.chara = chara;
        this.self = self;
//        this.object = object;
        this.prove = prove;
//        PrintChar.string = string;  // 静态时这样操作
//        this.string = string;
    }
    /********** Begin **********/

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        PrintChar ta = new PrintChar("E", a, b);
        PrintChar tb = new PrintChar("D",  b, c);
        PrintChar tc = new PrintChar("U",  c, a);
//        Thread ta = new Thread("Thread_1", a);  形成了一个循环轮回

        new Thread(ta).start();  // 使用接口之后，这样开启线程
        Thread.sleep(100);  // 确保按顺序a,b,c进行
        new Thread(tb).start();  // 使用接口之后，这样开启线程
        Thread.sleep(100);  // 确保按顺序a,b,c进行
        new Thread(tc).start();  // 使用接口之后，这样开启线程
//        PrintChar
    }


    @Override
    public void run() {
        // 线程同步此方法
        // 过程：通知b，进行a，进行完a等待；通知c，进行b，进行完b等待；通知a，进行c，进行完c等待。
        for (int i = 0; i < 5; i++) {
            synchronized (self) {  // 相当于利用这两个对象来上锁解锁操作（人为加锁）
                synchronized (prove) {
                    System.out.print(chara);
                    prove.notify();  // 唤醒下一个线程等待
//                    prove.notifyAll();
                }

                try {
//                        Thread.sleep(1000);
                    self.wait();  // 进行完等待当前的线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.exit(0);  // 退出Java虚拟机
    }
/********** End **********/

}
