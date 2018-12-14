package com.jxau.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 连接工具类 
 * 为什么做？java 面向对象 设计原则 【单一职责】【重用】
 * @author fujiansheng
 */
public class JDBCUtils {
	// 加载驱动，并建立数据库连接
	public static Connection getConnection() throws SQLException,
			ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		//【网络编程  计算机网络】
		String url = "jdbc:mysql://127.0.0.1:3306/android";
		String username = "root";
    	String password = "123";
		Connection conn = DriverManager.getConnection(url, username, 
				password);
		return conn;
	}
	// 关闭数据库连接，释放资源
	public static void release(Statement stmt, Connection conn) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
     public static void release(ResultSet rs, Statement stmt, 
     		Connection conn){
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		release(stmt, conn);
	}
}
