package com.jxau.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jxau.utils.JDBCUtils;


import com.jxau.model.User;


public class UserDao extends BaseDao{//碎片化 ---》   时间的利用率  
	
	
	public UserDao() {
		
	}
	
	// 查询所有的User对象 【显示所有数据】
		public ArrayList<User> findUserAll() {
			//定义一个自定义的 【数据结构 】 存储数据
			ArrayList<User> list = new ArrayList<User>();
			// 发送SQL语句
			String sql = "SELECT * FROM user";
			try {
				list = ((ArrayList<User>) this.query(sql, new BeanListHandler<>(User.class)));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		// 根据id查找指定的user
		public User find(String username) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				// 获得数据的连接
				conn = JDBCUtils.getConnection();
				// 获得Statement对象
				stmt = conn.createStatement();
				// 发送SQL语句'"+username+"'"
				String sql = "SELECT * FROM user where username='"+username+"'";
				//!!!!不能下面这种形式，会报错Unknown column 'admin' in 'where clause'
				//String sql = "SELECT * FROM user where username="+username;
				//System.out.println(sql);
				rs = stmt.executeQuery(sql);
				// 处理结果集
				while (rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setImage(rs.getString("image"));
					return user;
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.release(rs, stmt, conn);
			}
			return null;
		}
	
	/*	public static void main(String[] args) {
			UserDao userDao = new UserDao();
			User user = userDao.find("admin");
			System.out.println(user);
		}*/
		
		
		
		//添加用户的操作[注册]
		public boolean insert(User user){
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
		    String sql = "INSERT INTO user(image,username,password,phone,email) "+
						"VALUES('img/panda.png"+"','"
						+ user.getUsername()+ "','"
						+ user.getPassword()+ "','"
						+user.getPhone()+"','"
						+ user.getEmail()+ "')";
				int num = stmt.executeUpdate(sql);
				if (num > 0) {
					//根据用户名在另一张表中创建一个用户
					MomentDao momentDao = new MomentDao();
					momentDao.insertIntoMoment(user);
					return true;
				}
				return false;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.release(rs, stmt, conn);
			}
				return false;
		}
}
