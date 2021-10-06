package com.school.model;

import java.io.Serializable;

public class ReferralLoginRequest implements Serializable {

	private static final long serialVersionUID = -5290797454798315579L;

	private String username;

	public ReferralLoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
