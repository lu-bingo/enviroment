package com.briup.recive;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Collection;

import com.briup.bean.Enviroment;
import com.briup.store.IStore;
import com.briup.store.StoreImpl;

/**
 * 自定义线程类
 * @author ASUS
 *
 */
public class MyThread extends Thread{

	private Socket socket;

	public MyThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				//构建反序列化流
				InputStream stream = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(stream);
				Object object = ois.readObject();
				
				//反序列化为Collection<Enviroment>
				Collection<Enviroment> coll = (Collection<Enviroment>) object;
				
				//需要对数据进行存储
				IStore store = new StoreImpl();
				store.store(coll);
//				coll.forEach(System.out::println);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
}
