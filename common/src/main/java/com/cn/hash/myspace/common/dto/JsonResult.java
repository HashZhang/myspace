package com.cn.hash.myspace.common.dto;

/**
 * Json结果封装，用于前端传递
 *
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/4.
 */
public class JsonResult<T> {
	private String message;
	
	private String timestamp;
	
	private T data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
