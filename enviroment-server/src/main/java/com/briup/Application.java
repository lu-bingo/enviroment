package com.briup;

import com.briup.recive.IRecive;
import com.briup.recive.ReciveImpl;

/**
 * Server程序入口
 * @author ASUS
 *
 */
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IRecive recive = new ReciveImpl();
		recive.recive();
		
	}

}
