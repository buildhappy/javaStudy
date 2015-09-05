package com.buildhappy.concurrentInPractice.ch03shareObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 使用ThreadLocal来保证每个线程都拥有自己的链接
 * @author buildhappy
 *
 */
public class P37ConnectionHolder {
	public static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>(){
		public Connection initialValue(){
			try {
				return DriverManager.getConnection("DB_URL");
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	};
	
	public static Connection getConnection(){
		return connectionHolder.get();
	}
	
	public static void main(String[] args) {
		
	}

}
