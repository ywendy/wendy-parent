package com.wendy.structures.wheel;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/11/13.
 */
public class OneShotRegistration<T> extends CompletableFuture<T> implements Registration<T> {


    private final Callable<T> callable;
    protected volatile int rounds;
    protected volatile Status status;

    private final long delay;


    public OneShotRegistration(Callable<T> callable, int rounds, long delay) {
        this.callable = callable;
        this.rounds = rounds;
        this.delay = delay;
        this.status = Status.READY;
    }

    @Override
    public int rounds() {
        return rounds;
    }

    @Override
    public void decrement() {

        rounds -= 1;
    }


    @Override
    public boolean ready() {
        return rounds == 0;
    }

    @Override
    public void rest() {
        throw new RuntimeException("One Shot Registrations can not be rescheduled");

    }

    @Override
    public int getOffset() {
        throw new RuntimeException("One Shot Registration can not be rescheduled");
    }

    @Override
    public boolean isCancelAfterUse() {
        return true;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return delay;
    }

    @Override
    public void run() {
        try {
            this.complete(callable.call());
        } catch (Exception e) {
            this.completeExceptionally(e);
        }

    }
}
