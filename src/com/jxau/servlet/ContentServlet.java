package com.jxau.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxau.dao.ContentDao;
import com.jxau.dao.MomentDao;

/**
 * 接受发过来的的内容，写入到数据库表moments，并返回给安卓端
 * 
 * 现在只会在在自己的mood里插入一条数据，别的账户并不能看到
 * 写一个方法，将当前用户的mood插入到数据库所有用户的mood里
 * 
 * 可以做一个删除动态的功能
 */
@WebServlet("/ContentServlet")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ContentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//设置请求编码
		request.setCharacterEncoding("utf-8");
		//设置响应编码
		response.setContentType("text/html;charset=utf-8");
		//接收username和content
		String status = request.getParameter("status");
		String username = request.getParameter("username");
		String content = request.getParameter("content");
		String moodimg = request.getParameter("moodimg");
		System.out.println("传进来的username="+username+",content="+content+",status:"+status+",moodimg="+moodimg+"");
		ContentDao contentDao = new ContentDao();
	
		contentDao.InsertContentByUsername(username,content,status,moodimg);
		response.getWriter().write("true");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
