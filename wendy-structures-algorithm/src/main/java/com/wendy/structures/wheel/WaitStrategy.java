package com.wendy.structures.wheel;

/**
 * Created by Administrator on 2017/11/11.
 */
public interface WaitStrategy {


    public void waitUntil(long deadlineNanoseconds) throws InterruptedException;


    public static class YieldingWait implements WaitStrategy {
        @Override
        public void waitUntil(long deadline) throws InterruptedException {

            while (deadline >= System.nanoTime()) {
                Thread.yield();
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
            }

        }
    }


    public static class BusySpinWait implements WaitStrategy {
        @Override
        public void waitUntil(long deadline) throws InterruptedException {

            while (deadline >= System.nanoTime()) {
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
            }

        }
    }

    public static class SleepWait implements WaitStrategy {
        @Override
        public void waitUntil(long deadline) throws InterruptedException {
            long sleepTimeNanos = deadline - System.nanoTime();
            if (sleepTimeNanos > 0) {
                long sleepTimeMillis = sleepTimeNanos / 1000000;
                int sleepTimeNano = (int) (sleepTimeNanos - sleepTimeMillis * 1000000);
                Thread.sleep(sleepTimeMillis, sleepTimeNano);
            }
        }
    }


}
