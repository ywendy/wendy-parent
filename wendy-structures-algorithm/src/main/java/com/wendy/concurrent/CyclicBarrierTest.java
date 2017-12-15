package com.wendy.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *CyclicBarrier 和 CountDownLatch 有很多相同点,但是他们也有区别:
 * 1、CyclicBarrier 可以被reset到初始状态，关联他们的计数器都被初始化.
 * 当执行reset方法的时候，其他正在等待的线程会抛出brokenBarrierException，做的复杂一点的应用，当被打断时
 * 可以重新计算或者回复到计算点。
 *
 *
 *
 * 2017/12/15.
 */
public class CyclicBarrierTest {


    public static void main(String[] args) {
        System.out.println("4 lanes are created , race will start "+ "as soon as all player arrives.");
        CyclicBarrier raceBarrier = new CyclicBarrier(4,new Race());

        new Player(raceBarrier,"Ferrai").start();
        new Player(raceBarrier,"BMw").start();
        new Player(raceBarrier,"Skoda").start();
        new Player(raceBarrier,"Ford").start();

        raceBarrier.reset();
        new Player(raceBarrier,"BMW k1200S").start();
        new Player(raceBarrier,"Ducati 1098s").start();
        new Player(raceBarrier,"Aprilla RSV 1000R").start();
        new Player(raceBarrier,"Yamaha YZF R1").start();

    }
}



class Race implements Runnable{

    @Override
    public void run() {
        System.out.println("All player are ready to race.");
    }
}

class Player extends Thread{
    CyclicBarrier waitPoint;
    public Player(CyclicBarrier waitPoint,String name){
        super(name);
        this.waitPoint = waitPoint;
    }

    @Override
    public void run() {
        System.out.println("Player "+getName()+" is ready to race.");
        try {
            this.waitPoint.await();
        } catch (InterruptedException e) {
        } catch (BrokenBarrierException e) {
        }
    }



}
