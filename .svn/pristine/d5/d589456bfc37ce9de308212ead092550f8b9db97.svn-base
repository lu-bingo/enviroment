package com.briup.send;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;

import com.briup.bean.Enviroment;
import com.briup.util.FileBackup;
import com.briup.util.FileNameEnums;

public class SendImpl implements ISend {

	/**
	 * 把 collection 发送出去
	 * socket  tcp/ip
	 * 客户端发送数据的过程发生异常，
	 * 就会把采集好的数据进行备份
	 * 当下一次发送时将备份数据加载进来
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void send(Collection<Enviroment> collection) {
		// TODO Auto-generated method stub
		try {
			
			//发送之前需要将之前备份的数据加载进来
			Object obj = FileBackup.recover(FileNameEnums.CLIENT_EXDATA_PATH.getPath(), true);
			
			if (obj != null) {
				collection.addAll((Collection<? extends Enviroment>) obj);
			}
			//创建Socket
			Socket socket = new Socket("127.0.0.1",9998);

			//构建对象输出流/序列化流
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			
			//序列化
			oos.writeObject(collection);
			
			//刷新
			oos.flush();
			
			//关闭资源
			oos.close();
			socket.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				FileBackup.store(FileNameEnums.CLIENT_EXDATA_PATH.getPath(), collection);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
