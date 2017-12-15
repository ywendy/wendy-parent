package com.wendy.structures.wheel;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017/11/13.
 */
public class FixedRateRegistration<T> extends OneShotRegistration<T> {
    private final int rescheduleRounds;
    private final int scheduleOffset;


    public FixedRateRegistration(Callable<T> callable, int rounds, long delay,int rescheduleRounds,int scheduleOffset) {
        super(callable, rounds, delay);
        this.rescheduleRounds = rescheduleRounds;
        this.scheduleOffset = scheduleOffset;

    }

    @Override
    public void rest() {
            this.status = Status.READY;
            this.rounds = rescheduleRounds;
    }

    @Override
    public int getOffset() {
        return this.scheduleOffset;
    }

    @Override
    public boolean isCancelAfterUse() {
        return false;
    }


}
