package com.school.model;

public class ReferralLoginResponse {
	private String token;
	private ReferralPartner user;
	private String data;

	public ReferralLoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ReferralPartner getUser() {
		return user;
	}

	public void setUser(ReferralPartner user) {
		this.user = user;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ReferralLoginResponse [token=" + token + ", user=" + user + ", data=" + data + "]";
	}

}
