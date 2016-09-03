package com.cn.safety.model;

import java.io.Serializable;

public class UserRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	
	private String userName;

    private String password;

    private Integer age;
    
    private String homecoordinate;//家的坐标

	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRequest(String userId, String userName, String password, Integer age,String homecoordinate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.age = age;
		this.homecoordinate = homecoordinate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getHomecoordinate() {
		return homecoordinate;
	}

	public void setHomecoordinate(String homecoordinate) {
		this.homecoordinate = homecoordinate;
	}
    
    
    
}
