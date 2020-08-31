package com.briup.gather;
/**
 * 对原始数据进行解析
 * @author ASUS
 *
 */

import java.util.Collection;

import com.briup.bean.Enviroment;

public interface IGather {

	/**
	 * 专门进行原始数据的分析
	 * 1.把原始数据解析java识别的对象
	 * 2.把解析好的对象存入容器中
	 */
	
	Collection<Enviroment> gather();
}
