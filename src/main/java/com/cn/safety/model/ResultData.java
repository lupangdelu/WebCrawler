package com.cn.safety.model;

import java.io.Serializable;

/**
 * 请求返回结果数据
 * @author Tech
 *
 */
public class ResultData<T> implements Serializable {
	 private static final long serialVersionUID = 1L;

	 private int status;//返回 0：失败；1：成功

	 private T data;//具体的对象

	 private String message;//错误时消息

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	 
	 
}
