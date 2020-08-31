package com.briup;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.briup.gather.GatherImpl;
import com.briup.gather.IGather;
import com.briup.send.ISend;
import com.briup.send.SendImpl;

public class Application {

	public static void main(String[] args) {
		//创建定时器线程池
		ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
		
		//创建采集对象
		IGather gather = new GatherImpl();
		
		//创建发送对对象
		ISend send = new SendImpl();
		
		//创建任务并执行
		//程序运行后  推迟1S 后执行任务，以后每隔600S执行一次
		threadPool.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				send.send(gather.gather());
			}
		}, 1, 600, TimeUnit.SECONDS);
	}
}
