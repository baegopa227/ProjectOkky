package com.okky.utils;

import java.io.FileNotFoundException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;

public class ConfProperties extends java.util.Properties{

	public static ConfProperties prop;
	
	private ConfProperties() {
		try {
			Reader r = Resources.getResourceAsReader("conf.properties");
			
			this.load(r);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static ConfProperties getInstance() {
		
		if(prop == null) {
			prop = new ConfProperties();
		}
		
		return prop;
	}
}
