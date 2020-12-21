package com.okky.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.okky.utils.CookieUtil;
import com.okky.utils.Crypto;
import com.okky.utils.JwtUtil;

import io.jsonwebtoken.Claims;

@Controller
public class OkkyInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private JwtUtil jwtUtil;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		StringBuffer url = request.getRequestURL();
		
		//String token = request.getHeader("Authorization");
	    //Claims claims =  jwtUtil.getClaims(token);
	    
	    return true;
	}
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	
	
}
