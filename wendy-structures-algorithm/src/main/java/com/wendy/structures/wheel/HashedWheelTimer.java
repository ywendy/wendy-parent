package com.wendy.structures.wheel;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * hash结构时间轮.
 * 2017/11/1.
 */
public class HashedWheelTimer implements ScheduledExecutorService {

    public static final long DEFAULT_RESOLUTION = TimeUnit.NANOSECONDS.convert(10, TimeUnit.MILLISECONDS);

    public static final int DEFAULT_WHEEL_SIZE = 512;
    private static final String DEFAULT_TIMER_NAME = "hashed-wheel-timer";
    private final Set<Registration<?>>[] wheel;
    private final int wheelSize;
    private final long resolution;
    private final ExecutorService loop;
    private final ExecutorService executor;
    private final WaitStrategy waitStrategy;
    private volatile int cursor = 0;


    public HashedWheelTimer(String name, long res, int wheelSize, WaitStrategy strategy, ExecutorService exec) {
        this.wheelSize = wheelSize;
        this.waitStrategy = strategy;
        this.wheel = new Set[wheelSize];
        for (int i = 0; i < wheelSize; i++) {
            wheel[i] = new ConcurrentSkipListSet<>();
        }
        this.resolution = res;
        final Runnable loopRunnable = new Runnable() {
            @Override
            public void run() {
                long deadline = System.nanoTime();
                while (true) {
                    Set<Registration<?>> registrations = wheel[cursor];

                    for (Registration r : registrations) {
                        if (r.isCancelled()) {
                            registrations.remove(r);
                        } else if (r.ready()) {
                            executor.execute(r);
                            registrations.remove(r);
                            if (!r.isCancelAfterUse()) {
                                // TODO: 2017/11/11 重新调度他
                            }
                        } else {
                            r.decrement();
                        }
                    }

                    deadline += resolution;
                    try {
                        waitStrategy.waitUntil(deadline);
                    } catch (InterruptedException e) {
                        return;
                    }


                    cursor = (cursor + 1) % wheelSize;
                }
            }
        };

        this.loop = Executors.newSingleThreadExecutor(new ThreadFactory() {
            AtomicInteger i = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, name + "-" + i.getAndIncrement());
                thread.setDaemon(true);
                return thread;
            }
        });
        this.loop.submit(loopRunnable);
        this.executor = exec;


    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return null;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return null;
    }

    @Override
    public ScheduledFuture<?> submit(Runnable task) {
        return scheduleOneShot(resolution, constantlyNull(task));
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }


    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return scheduleOneShot(TimeUnit.NANOSECONDS.convert(delay, unit), constantlyNull(command));
    }

    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        return null;
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return null;
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        return null;
    }

    private <V> Registration<V> scheduleOneShot(long firstDelay, Callable<V> callable) {
        assertRunning();
        isTrue(firstDelay >= resolution, "Cannot schedule tasks amount of time less than timer precision.");
        // TODO: 2017/11/11
        int firstFireOffset = (int) (firstDelay / resolution);
        int firstFireRounds = firstFireOffset / wheelSize;

        Registration<V> r = new OneShotRegistration<>(callable, firstFireRounds, firstDelay);

        wheel[idx(cursor + firstFireOffset + 1)].add(r);
        return r;

    }

    private <V> Registration<V> scheduleFixedRate(long recurringTimeout, long firstDelay, Callable<V> callable) {

        assertRunning();
        isTrue(recurringTimeout >= resolution, "Cannot schedule tasks for amount of time less than timer precision.");

        int offset = (int) (recurringTimeout / resolution);
        int rounds = offset / wheelSize;
        int firstFireOffset = (int) (firstDelay / resolution);
        int firstFireRounds = firstFireOffset / wheelSize;
        Registration<V> r = new FixedRateRegistration<V>(callable, firstFireRounds, recurringTimeout, rounds, offset);
        wheel[idx(cursor + firstFireOffset + 1)].add(r);
        return r;

    }


    private void assertRunning() {
        if (this.loop.isTerminated()) {
            throw new IllegalStateException("Timer is not running");
        }
    }


    private static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    private static Callable<?> constantlyNull(Runnable r) {
        return () -> {
            r.run();
            return null;
        };
    }

    private int idx(int cursor) {
        return cursor % wheelSize;
    }


    @Override
    public void execute(Runnable command) {

    }
}
