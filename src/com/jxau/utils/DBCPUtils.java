package com.jxau.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * 获取dpcp连接池数据源类
 * 
 * @author Administrator
 */
public class DBCPUtils {

	private static DataSource ds = null;
	static {
		// 新建一个配置文件对象
		Properties prop = new Properties();
		try {
			// 通过类加载器找到文件路径，读配置文件
			InputStream in = new DBCPUtils().getClass().getClassLoader().getResourceAsStream("dbcpconfig.properties");
			// 把文件以输入流的形式加载到配置对象中
			prop.load(in);
			// 创建数据源对象
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static DataSource getDataSource() {
		return ds;
	}

	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			// TODO 记录日志
			return null;
		}
	}

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