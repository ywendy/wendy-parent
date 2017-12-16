package com.wendy.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 一个房间里有4台ATM ，控制每次只能有4个人进入房间使用
 * <p>
 * <p>
 * <p>
 * <p>
 * 第二种场景：
 * 如果只有一台ATM，每次只能一个人进入，就是Mutex互斥的，这时只要Semaphore控制的数量变为1 即可
 * <p>
 * 2017/12/15.
 */
public class SemaphoreTest {

    static Semaphore semaphore = new Semaphore(4);

    public static void main(String[] args) {
        System.out.println("Total available Semaphore permits: " + semaphore.availablePermits());

        MyATMThread t1 = new MyATMThread("A");
        MyATMThread t2 = new MyATMThread("B");
        MyATMThread t3 = new MyATMThread("C");
        MyATMThread t4 = new MyATMThread("D");
        MyATMThread t5 = new MyATMThread("E");
        MyATMThread t6 = new MyATMThread("F");
        MyATMThread t7 = new MyATMThread("G");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();


        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    static class MyATMThread extends Thread {
        String name = "";

        MyATMThread(String name) {
            this.name = name;
        }


        @Override
        public void run() {
            System.out.println(this.name + ": acquiring lock...");
            System.out.println(this.name + ": available Semaphore permits now : " + semaphore.availablePermits());

            try {
                semaphore.acquire();

                System.out.println(this.name + ": got the permit!");
                try {
                    for (int i = 1; i < 5; i++) {
                        System.out.println(this.name + ": is performing operation " + i + ",available Semaphore permits :" + semaphore.availablePermits());
                    }
                    TimeUnit.SECONDS.sleep(1);
                } finally {
                    System.out.println(this.name + ": releasing lock...");
                    semaphore.release();
                    System.out.println(this.name + ": available Semaphore permits now:" + semaphore.availablePermits());
                }

            } catch (InterruptedException e) {

            }

        }
    }


}
