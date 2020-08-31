package com.briup.gether;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientTest {
	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

		/*
		 * scheduledExecutorService.schedule(new Runnable() {
		 * 
		 * @Override public void run() {
		 * System.out.println("------------任务1--------------"); } }, 1,
		 * TimeUnit.SECONDS);
		 */
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			System.out.println("---------任务1 start-----------");
			System.out.println("---------任务1 aaa-----------");
			System.out.println("---------任务1 bbb-----------");
			int a = 10 / 0;
			System.out.println("---------任务1 ccc-----------");
			System.out.println("---------任务1 end-----------");
		}, 2, 2, TimeUnit.SECONDS);
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			System.out.println("---------任务2 start-----------");
			System.out.println("---------任务2 aaa-----------");
			System.out.println("---------任务2 bbb-----------");
			System.out.println("---------任务2 ccc-----------");
			System.out.println("---------任务2 end-----------");
		}, 2, 2, TimeUnit.SECONDS);

	}
}
