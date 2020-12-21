package com.okky.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.okky.okkyEnum.EMType;
import com.okky.okkyEnum.EType;

public class ErrorVO {

	private final Logger log = LoggerFactory.getLogger(ErrorVO.class);
	
	private String eTypeStr;
	private String emTypeStr;
	
	public void setErrorInfo(EType error, EMType errorMessage) {
		
		this.eTypeStr = error.toString();
		this.emTypeStr = errorMessage.toString();
		
	}

	public String geteTypeStr() {
		return eTypeStr;
	}

	public void seteTypeStr(String eTypeStr) {
		this.eTypeStr = eTypeStr;
	}

	public String getEmTypeStr() {
		return emTypeStr;
	}

	public void setEmTypeStr(String emTypeStr) {
		this.emTypeStr = emTypeStr;
	}
	
	
}
