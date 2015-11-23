package com.kdyzm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kdyzm.domain.User;

/**
 * 不做登录验证，只做自动登录
 * @author kdyzm
 *
 */
public class AutoLoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("过滤器初始化！");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		HttpSession session=request.getSession();
		System.out.println("被拦截器过滤！");
		//先判断是否已经登陆再做其他的判断比较高效
		if(session.getAttribute("user")==null){
			System.out.println("还没有登陆！");
			Cookie [] cookies=request.getCookies();
			if(cookies!=null){
				for(Cookie cookie:cookies){
					String name=cookie.getName();
					if(name.equals("userName")){
						System.out.println("还没有登录，但是存在登录信息！");
						User user=new User();
						String userName=cookie.getValue();
						String password=userName;
						user.setUserName(userName);
						user.setPassword(password);
						session.setAttribute("user", user);
					}
					System.out.println(cookie.getName()+":"+cookie.getValue());
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("过滤器被销毁！");
	}
	
}
