package com.jxau.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxau.dao.UserDao;

import com.jxau.model.User;

/**
 * 接收来自安卓登录的请求，并且通过数据库验证账户准确性
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("GET方法");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean flag = false;
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		System.out.println("name:" + username + "&&" + "password:" + password);

		UserDao userDao = new UserDao();
		User user = userDao.find(username);
		if (user != null && user.getPassword().equals(password)) {
			System.out.println("登录验证通过");
			flag = true;
			response.getWriter().write(String.valueOf(flag));
		}

	}

}
