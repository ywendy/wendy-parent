package com.wendy.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 三个工人为老板干活，当三个人把一天的活都干完的时候，老板来检查工作。
 * 设计：Worker(工人),Boss (老板)
 * <p>
 * 2017/12/15.
 */
public class CountDownLatchTest1 {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService exec = Executors.newCachedThreadPool();
        Worker worker1 = new Worker("tony", countDownLatch);
        Worker worker2 = new Worker("wendy", countDownLatch);
        Worker worker3 = new Worker("polly", countDownLatch);

        Boss boss = new Boss(countDownLatch);

        exec.submit(worker1);
        exec.submit(worker2);
        exec.submit(worker3);

        exec.execute(boss);
        exec.shutdown();

    }

}


class Worker implements Runnable {

    private String name;
    private CountDownLatch downLatch;

    public Worker(String name, CountDownLatch downLatch) {
        this.name = name;
        this.downLatch = downLatch;
    }

    @Override
    public void run() {
        this.work();
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(this.name + "活干完了！");
            this.downLatch.countDown();
        }
    }

    private void work() {
        System.out.println(this.name + " 正在工作.");
    }

}


class Boss implements Runnable {

    private CountDownLatch downLatch;

    public Boss(CountDownLatch downLatch) {
        this.downLatch = downLatch;
    }

    @Override
    public void run() {
        System.out.println("Boss 正在等待所有的工人干完活");
        try {
            this.downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Worker 活都干完了,Boss 开始检查工作了.");
    }
}







