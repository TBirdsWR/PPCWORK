package com.taotao.pojo;

public class UserInfo {

	String id;
	
	String username;
	
	String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", userName=" + username + ", password=" + password + "]";
	}
	
	
}
