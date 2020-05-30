package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.UsersService;


@Component
public class AuthInterceptor implements HandlerInterceptor{
	@Autowired
	private UsersService userservice;      
	private static final Logger logger=
			LoggerFactory.getLogger(AuthInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//从hhandler中读取ttoken 用于判断用户是否登录
		String uri=request.getRequestURI();
		if (uri.startsWith("/user/login" ) || uri.startsWith("/user/register")) {  //登录程序本身免去身份�?�?
			return true;
		}
		if (response.getStatus() == HttpServletResponse.SC_FORBIDDEN) { //403出错也免去身份检�?
			return true;
		}
		logger.debug("response status:" +response.getStatus());

		boolean logged = false;		
		// 从hleader中读取ttoken，用于判断用户是否登�?
		String token = request.getHeader("token");
		if (null != token) { // 未登�?
			String username = userservice.currentUser(token);
			if (null != username) {
				logged = true;
			}
		}
		logger.debug("当前用户是否登录�?" + (logged ? "�?" : "不是"));
		if (!logged) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "未登录，禁止访问�?");
		}
     return logged;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
