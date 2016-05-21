package com.wendy.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.wendy.thread.schedule.ThreadFactoryImpl;

public class ScheduleThread {
	// 定时线程
	private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2,
			new ThreadFactoryImpl("ExpiredCouponScheduleThread"));
	// .newSingleThreadScheduledExecutor(new
	// ThreadFactoryImpl("ExpiredCouponScheduleThread"));

	public static void main(String[] args) {
		ScheduleThread st = new ScheduleThread();
		st.scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println("t1=>"+Thread.currentThread().getName());
			}
		}, 5, 10, TimeUnit.SECONDS);

		st.scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println("t2=>"+Thread.currentThread().getName());
			}
		}, 2, 5, TimeUnit.SECONDS);
	}

}
