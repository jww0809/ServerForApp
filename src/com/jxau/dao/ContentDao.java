package com.jxau.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.jxau.model.User;
import com.jxau.model.UserMoment;
import com.jxau.utils.DBCPUtils;
import com.jxau.utils.JDBCUtils;

/**
 * 将传进来的数据插入到数据库中
 * 
 * @author 52109
 *
 */
public class ContentDao {

	public boolean InsertContentByUsername(String username, String content, String status, String moodImg) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			UserDao userDao = new UserDao();
			User user = userDao.find(username);
			String newMood = "";
			String oldMood = new MomentDao().find(username);
			System.out.println(oldMood);
			String nowMood = "{\"headImg\":\"" + user.getImage() + "\",\"username\":\"" + user.getUsername()
					+ "\",\"mood\":\"" + content + "\", \"moodImg\":\"" + moodImg + "\"}";

			// 自己可见
			if (user != null) {
				if (status.equals("myself")) {
					newMood = nowMood + "--" + oldMood;
					String sql = "UPDATE moments set mood= '" + newMood + "'where username='" + username + "'";
					System.out.println(sql);
					int num = stmt.executeUpdate(sql);
					return num > 0 ? true : false;
				} else {
					// 所有人可见,要遍历旧mood为每一个用户插入新数据
					List<User> list = userDao.findUserAll();
					for (User temp : list) {
						String name = temp.getUsername();
						String peopleMood = new MomentDao().find(name);
						String newPeopleMood = "";
						if (peopleMood == null || peopleMood.equals("")) {
							newPeopleMood = nowMood;
							String sql = "UPDATE moments set mood= '" + newPeopleMood + "'where username='" + name
									+ "'";
							System.out.println(sql);
							stmt.executeUpdate(sql);
						} else {
							newPeopleMood = nowMood + "--" + peopleMood;
							String sql = "UPDATE moments set mood= '" + newPeopleMood + "'where username='" + name
									+ "'";
							System.out.println(sql);
							stmt.executeUpdate(sql);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return true;

	}

	/*
	 * public static void main(String[] args) { ContentDao contentDao = new
	 * ContentDao(); boolean flag =
	 * contentDao.InsertContentByUsername("ylg","我的第二条动态","myself","img/frog.png");
	 * System.out.println(flag); }
	 */

}
