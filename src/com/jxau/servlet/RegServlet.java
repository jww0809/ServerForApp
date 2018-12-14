package com.jxau.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxau.dao.UserDao;
import com.jxau.model.User;

/**
 * 如果注册的用户已经存在,应该返回提示信息
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public RegServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		user.setUsername(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		
		UserDao userDao = new UserDao();
		//为空就开始注册,不然提示"已存在"
		User user2 = userDao.find(user.getUsername());
		if(user2==null) {
			userDao.insert(user);
			response.getWriter().write("true");
		}else {
			response.getWriter().write("alreadyExist");
		}
		
	}

}
