package com.school.model;

import java.util.List;

public class UserLoginResponse {

	private String token;
	private UserPojo user;
	private List<UserRole> roles;
	private String data;
	
	public UserLoginResponse() {
		super();
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserPojo getUser() {
		return user;
	}
	public void setUser(UserPojo user) {
		this.user = user;
	}
	public List<UserRole> getRoles() {
		return roles;
	}
	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "UserLoginResponse [token=" + token + ", user=" + user + ", roles=" + roles + ", data=" + data + "]";
	}
	
	
}
