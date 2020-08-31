package com.briup.gether;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.briup.bean.Enviroment;
import com.briup.gather.GatherImpl;
import com.briup.gather.IGather;

public class GetherImplTest {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		GetherImpl getherImpl = new GetherImpl();
//		Collection<Enviroment> collection = getherImpl.gather();
//		collection.forEach(System.out::println);
//	}
	@Test
	public void t1() {
		IGather gather = new GatherImpl();
		Collection<Enviroment>collection = gather.gather();
		collection.forEach(System.out::println);
		System.out.println("size: "+collection.size());
	}
	
	@Test
	public void t5() {
		
		String ss = "";
		String ss1 = null;
		String ss2 = " ";
		System.out.println(StringUtils.isBlank(ss));;
		System.out.println(StringUtils.isBlank(ss1));;
		System.out.println(StringUtils.isBlank(ss2));;
	}

}
