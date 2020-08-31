package com.briup.gather;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.briup.bean.Enviroment;
import com.briup.util.FileBackup;
import com.briup.util.FileNameEnums;

public class GatherImpl implements IGather {

	@SuppressWarnings({ "resource", "null", "unchecked" })
	@Override
	public Collection<Enviroment> gather() {
		// TODO Auto-generated method stub
		/**
		 * radwtmp 一行行地读取数据 分割字符串 “|” BuffereReader() 将分割好的数据填充到Enviroment对象 将对象添加到集合并返回
		 */
		BufferedReader inBufferedReader = null;
		long num = 0;
		List<Enviroment> list = new ArrayList<>();
		try {

			inBufferedReader = new BufferedReader(new FileReader(FileNameEnums.CLIENT_RADWTMP_PATH.getPath()));

			// 记录字节数
			// 跳过多少字符
			Object object = FileBackup.recover(FileNameEnums.CLIENT_NUM_PATH.getPath(),true);
			if (object != null) {
				num += (Long) object;
			}

			inBufferedReader.skip(num);

			String line = null;
			// 解析之前需要把 原来存储在data.txt里面的数据加到list容器中
			Object dataObject = FileBackup.recover(FileNameEnums.CLIENT_DATA_PATH.getPath(),true);
			if (dataObject != null) {
				// 将原来的数据放入新的list集合
				list.addAll((Collection<? extends Enviroment>) dataObject);
			}
			while ((line = inBufferedReader.readLine()) != null) {

				// 回车和换行
				num += line.length() + 1;
				String arr[] = line.split("\\|");
				// 数组判空
				if (arr == null || arr.length == 0) {
					return null;
				}

				Enviroment enviroment = new Enviroment();
				String srcId = arr[0];
				String devId = arr[1];
				Long regionId = Long.parseLong(arr[2]);
				String name = arr[3];
				Long count = Long.parseLong(arr[4]);
				Integer state = Integer.parseInt(arr[5]);
				String date = arr[6];
				Integer reviceState = Integer.parseInt(arr[7]);
				String getDate = arr[8];

				enviroment.setSrcId(srcId);
				enviroment.setDevId(devId);
				enviroment.setRegionId(regionId);

				enviroment.setCount(count);
				enviroment.setState(state);
				enviroment.setReviceState(reviceState);
				enviroment.setGetDate(new Date(Long.parseLong(getDate)));

				if ("16".equals(name) && (date.length()) >= 8) {
					enviroment = new Enviroment();
					enviroment.setSrcId(srcId);
					enviroment.setDevId(devId);
					enviroment.setRegionId(regionId);

					enviroment.setCount(count);
					enviroment.setState(state);
					enviroment.setReviceState(reviceState);
					enviroment.setGetDate(new Date(Long.parseLong(getDate)));
					enviroment.setName("温度");
					String date1 = date.substring(0, 4);
					double dec1 = (double) (((float) Long.parseLong(date1, 16) * 0.00268127) - 46.85);
					enviroment.setData(dec1);
					list.add(enviroment);
//					
					enviroment = new Enviroment();
					enviroment.setSrcId(srcId);
					enviroment.setDevId(devId);
					enviroment.setRegionId(regionId);

					enviroment.setCount(count);
					enviroment.setState(state);
					enviroment.setReviceState(reviceState);
					enviroment.setGetDate(new Date(Long.parseLong(getDate)));
					enviroment.setName("湿度");
					String date2 = date.substring(4, 8);
					double dec2 = (double) (((float) Long.parseLong(date2, 16) * 0.00190735) - 6);
					enviroment.setData(dec2);
					list.add(enviroment);
				} else if ("1280".equals(name)) {
					enviroment.setName("CO2");
//					String date1 = date.substring(0, 4);
					double l = Integer.parseInt(date.substring(0, 4), 16);
					enviroment.setData(l);
					list.add(enviroment);
				} else if ("256".equals(name)) {
					enviroment.setName("光照强度");
//					String date1 = date.substring(0, 4);
					double l = Integer.parseInt(date.substring(0, 4), 16);
					enviroment.setData(l);
					list.add(enviroment);
				}

			}


			inBufferedReader.close();
			
			FileBackup.store(FileNameEnums.CLIENT_NUM_PATH.getPath(), num);
			
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			/**
			 * 发生异常解析好的数据存储在list集合
			 */
			try {
				FileBackup.store(FileNameEnums.CLIENT_DATA_PATH.getPath(), list);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}

	}

}
