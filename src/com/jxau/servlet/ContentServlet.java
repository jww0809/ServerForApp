package com.jxau.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxau.dao.ContentDao;
import com.jxau.dao.MomentDao;

/**
 * 接受发过来的的内容，写入到数据库表moments，并返回给安卓端
 */
@WebServlet("/ContentServlet")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ContentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//response.getWriter().write("nmsl");
			//接收username和content
		String username = request.getParameter("username");
		String content = request.getParameter("content");
		System.out.println("传进来的username="+username+",content="+content);
		ContentDao contentDao = new ContentDao();
		//存储成功,返回一个true给客户端;
		if(contentDao.InsertContentByUsername(username,content)) {
			response.getWriter().write("true");
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
