package com.jxau.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.jxau.model.User;
import com.jxau.model.UserMoment;
import com.jxau.utils.DBCPUtils;
import com.jxau.utils.JDBCUtils;

/**
 * 将传进来的数据插入到数据库中
 * @author 52109
 *
 */
public class ContentDao {
	
	public boolean InsertContentByUsername(String username,String content) {
		

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
		// 获得数据的连接
		conn = JDBCUtils.getConnection();
		// 获得Statement对象
		stmt = conn.createStatement();
		
		//将content转成一个mood形式的数据
		//UserMoment userMoment = new UserMoment(username);
		
		UserDao userDao = new UserDao();
		User user = userDao.find(username);
		//System.out.println(user);
		String mood = "{\"headImg\":\"" +user.getImage() + "\",\"username\":\""
				+ user.getUsername() + "\",\"mood\":\"" + content + "\"}";
		
		String newMood = mood+"--"+new MomentDao().find(username);
		
		// 发送SQL语句
	    String sql = "UPDATE moments set mood= '"+newMood+"'where username='"+username+"'";
	    //System.out.println(sql);
			int num = stmt.executeUpdate(sql);
			if (num > 0) {
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
	
/*	public static void main(String[] args) {
		ContentDao contentDao = new ContentDao();
		boolean flag = contentDao.InsertContentByUsername("jww","学你妈");
		System.out.println(flag);
	}*/

}
