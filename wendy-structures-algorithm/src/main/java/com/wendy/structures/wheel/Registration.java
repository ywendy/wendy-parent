package com.wendy.structures.wheel;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/11/11.
 */
interface Registration<T> extends ScheduledFuture<T>, Runnable {

    enum Status {
        CANCELLED,
        READY
    }

    int rounds();

    /**
     *
     */
    void decrement();

    /**
     * @return
     */
    boolean ready();


    void rest();

    int getOffset();

    boolean isCancelAfterUse();

    @Override
    boolean cancel(boolean mayInterruptIfRunning);

    @Override
    boolean isCancelled();

    @Override
    boolean isDone();


    @Override
    long getDelay(TimeUnit unit);


    @Override
    default int compareTo(Delayed o) {
        Registration other = (Registration) o;
        long r1 = rounds();
        long r2 = other.rounds();
        if (r1 == r2) {
            return other == this ? 0 : -1;
        } else {
            return Long.compare(r1, r2);
        }

    }

    @Override
    T get() throws InterruptedException, ExecutionException;

    @Override
    T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
}
