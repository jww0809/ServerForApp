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
 * 传进来三个参数，更新密码
 * @author 52109
 *
 */
@WebServlet("/ModifyPwdServlet")
public class ModifyPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ModifyPwdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		
		System.out.println("从安卓传过来的username的值"+username);
		UserDao userDao = new UserDao();
		User user = userDao.find(username);
		if(user!=null) {
			userDao.update(user,newpwd);
			response.getWriter().write("true");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
