package com.okky.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	public static String getAuthCookie(HttpServletRequest request, String cookieName) throws Exception {

		System.out.println("getAUthCookie");
		Cookie[] cookies = request.getCookies();
		System.out.println(cookies);
		if(cookies == null) return "";
		String value = "";
		String returnData = "";
		
		for(int i=0; i< cookies.length; i++) {
			if("asdf".equals(cookies[i].getName())) 
			{
				value = java.net.URLDecoder.decode(cookies[i].getValue(), "UTF-8");
				System.out.println("asdf cookie : " + value);
				break;
			}
		}
		
		String result = Crypto.decrptLogin(value);
		System.out.println("복호화 결과 : " + result);
		String datas[] = result.split("\\##");
		Map<String, String> m_cookies = new HashMap<String, String>();
		for(String data : datas) {
			String data_[] = data.split("=");
			if(data_.length == 2)m_cookies.put(data_[0],data_[1]);
			
		}
		
		returnData = m_cookies.get(cookieName);
		return returnData;
	}

	public static void setLoginCooies(HttpServletResponse response, String name, String value, boolean b) throws Exception {

		if(value == null) value = "";
		response.setCharacterEncoding("UTF-8");
		response.setHeader("P3P","CP='NOI CURa ADMa DEVa TAIa OUR DELa BUS IND PHY ONL UNI COM NAV INT DEM PRE'");
		value = java.net.URLEncoder.encode(value, "UTF-8");
		Cookie cookie = new Cookie(name, value);
		cookie.setDomain("localhost");
		cookie.setPath("/");
		response.addCookie(cookie);
		
	}

}
