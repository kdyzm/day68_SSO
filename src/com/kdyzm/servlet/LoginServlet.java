package com.kdyzm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kdyzm.domain.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -7472135565931819576L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		if(userName.equals(password)){
			User user=new User();
			user.setUserName(userName);
			user.setPassword(password);
			//保存到Session
			request.getSession().setAttribute("user", user);
			//保存到Cookie
			Cookie cookie=new Cookie("userName",user.getUserName());
			cookie.setMaxAge(3600);				//设置生命周期为一个小时
			cookie.setDomain(".kdyzm.com");		//设置域名
			cookie.setPath("/");				//设置路径为根路径
			response.addCookie(cookie);
		}
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
}
