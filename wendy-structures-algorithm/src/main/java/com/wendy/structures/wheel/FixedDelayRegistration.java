package com.wendy.structures.wheel;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * Created by Administrator on 2017/11/13.
 */
public class FixedDelayRegistration<T> extends OneShotRegistration<T> {

    private final int rescheduleRounds;
    private final int scheudleOffset;
    private final Consumer<Registration<?>> rescheduleCallback;

    public FixedDelayRegistration(Callable<T> callable, int rounds, long delay, int scheduleRounds, int scheduleOffset,
                                  Consumer<Registration<?>> rescheduleCallback) {
        super(callable, rounds, delay);
        this.rescheduleRounds = scheduleRounds;
        this.scheudleOffset = scheduleOffset;
        this.rescheduleCallback = rescheduleCallback;
    }

    @Override
    public int getOffset() {
        return this.scheudleOffset;
    }

    @Override
    public boolean isCancelAfterUse() {
        return true;
    }

    @Override
    public void run() {
        super.run();
        rescheduleCallback.accept(this);
    }
}
