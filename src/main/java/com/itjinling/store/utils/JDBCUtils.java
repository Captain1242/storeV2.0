package com.itjinling.store.utils;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
 * JDBC的工具类：
 */
public class JDBCUtils {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/store_v2.0";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	/*
	 * 创建连接池BasicDataSource
	 */
	public static BasicDataSource dataSource = new BasicDataSource();
	//静态代码块
	static {
		//对连接池对象 进行基本的配置
		dataSource.setDriverClassName(DRIVER); // 这是要连接的数据库的驱动
		dataSource.setUrl(URL); //指定要连接的数据库地址
		dataSource.setUsername(USERNAME); //指定要连接数据的用户名
		dataSource.setPassword(PASSWORD); //指定要连接数据的密码
	}
	/*
	 * 返回连接池对象
	 */
	public static BasicDataSource getDataSource(){
		return dataSource;
	}



	
}
