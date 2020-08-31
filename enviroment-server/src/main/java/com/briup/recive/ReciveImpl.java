package com.briup.recive;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class ReciveImpl implements IRecive {

	/**
	 * 
	 * 客户端现在会每隔10min发送一次数据 后一次的数据需要等前一次处理完了以后 才会去处理。这明显不符合效率问题。 因此当客户端发送一次数据过来，那么我们就
	 * 开启一个线程去处理。
	 */

	private static final Logger logger = Logger.getLogger(ReciveImpl.class);

	@Override
	public void recive() {

		try {
			@SuppressWarnings("resource")
			
			// 创建ServerSocket对象
			ServerSocket serverSocket = new ServerSocket(9998);

			/*
			 * 当accept执行完成，那么三次握手就会建立成功 返回的socket里面就包含了客户端发送过来的数据 服务器是永远开启的
			 */
			logger.info("准备接收"+serverSocket.getInetAddress()+"发送过来的数据");

			while (true) {
				Socket socket = serverSocket.accept();

				logger.info("正在接收"+socket.getInetAddress()+"发送过来:数据");
				new MyThread(socket).start();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
