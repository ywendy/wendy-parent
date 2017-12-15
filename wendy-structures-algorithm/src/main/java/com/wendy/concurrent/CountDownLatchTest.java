package com.wendy.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/12/15.
 */
public class CountDownLatchTest {

    //百米赛跑，10名选手，枪响起跑，所有人到达终点，比赛结束
    public static void main(String[] args) throws InterruptedException {


        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(10);
        final ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int index =0;index <10;index++){
            final  int NO = index+1;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        begin.await();
                        //Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("NO."+NO+" arrived.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        end.countDown();
                    }
                }
            };

            exec.submit(run);
        }

        begin.countDown();
        System.out.println("game start........");

        end.await();
        System.out.println("game over..........");
        exec.shutdown();


    }


}
