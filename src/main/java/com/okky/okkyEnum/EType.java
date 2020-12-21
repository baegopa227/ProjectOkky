package com.okky.okkyEnum;

public enum EType {
	errorNone(0), clientError(100), applicationError(101), fileError(102), DBError(103);

	final private int code;

	private EType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}