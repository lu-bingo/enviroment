package com.briup.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;

public class ConnecitonUtils {

	//声明连接池
	private static DruidDataSource datasource = null;

	static {
		/*
		 * 可以将数据库连接信息提取到一个properties文件里面去
		 *  然后把properties里面的数据加载进来，一旦修改了properties数据库
		 *  连接信息，那么就会把数据库的来连接改变，这样我们就需要修改任何
		 *  java代码从而去改变数据库连接
		 */
		
		//读取properties文件
		
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/main/resources/jdbc.properties"));
		
			datasource = new DruidDataSource();
			
			datasource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
			datasource.setUrl(properties.getProperty("jdbc.url"));
			datasource.setUsername(properties.getProperty("jdbc.username"));
			datasource.setPassword(properties.getProperty("jdbc.password"));
			
			datasource.setInitialSize(Integer.parseInt(properties.getProperty("jdbc.init")));
			datasource.setMaxActive(Integer.parseInt(properties.getProperty("jdbc.max")));
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static Connection getConnection(boolean autoCommit) throws SQLException {
		Connection connection = datasource.getConnection();
		
		connection.setAutoCommit(autoCommit);
		return connection;
	}
	
	public static Connection getConnection() throws SQLException {
		return getConnection(false);
	}

}
