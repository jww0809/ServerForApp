package com.jxau.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.jxau.model.User;
import com.jxau.utils.JDBCUtils;
import com.jxau.model.UserMoment;
import com.jxau.utils.DBCPUtils;

/**
 * 从moments数据库获取数据
 * 
 * @author 52109
 *
 */
public class MomentDao extends BaseDao {

	// 根据用户名找到他的朋友圈列表信息(mood)
	public String find(String username) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT mood FROM moments WHERE username='" + username + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				return rs.getString("mood");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return "";
	}

	// 根据传来的用户名在数据库新增一行
	// 传进来一个user，里面有属性name，pwd
	public boolean insertIntoMoment(User user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 这里怎么样可以根据用户名插入一个默认的mood,这样刚注册的用户登录进去动态才不会显示为空
			// "{"headImg":"img/head2.png","uname":"ls","mood":"我是老王!嘿嘿嘿"}"
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO moments(username,mood)" + "  VALUES('" + user.getUsername() + "','" + "')";
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

	/*
	 * public UserMoment findByUsername(String username) throws SQLException {
	 * QueryRunner queryRunner = new QueryRunner(DBCPUtils.getDataSource()); String
	 * sql = "select * from moments where username=?"; UserMoment userMoment =
	 * (UserMoment) queryRunner.query(sql, new BeanHandler(UserMoment.class), new
	 * Object[] {username}); return userMoment; }
	 */
	/*
	 * public static void main(String[] args) throws SQLException { MomentDao
	 * momentDao = new MomentDao(); String userMoment = momentDao.find("xxc");
	 * System.out.println(userMoment);
	 * 
	 * }
	 */
}
