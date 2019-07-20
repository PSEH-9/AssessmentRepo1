package com.sapient.bisht.sid.weather.dto;

import lombok.Data;

@Data
public class ErrorMessage {
	String errorMsg;
	String errorCode;
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
