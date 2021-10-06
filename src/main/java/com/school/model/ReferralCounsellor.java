package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "referralcounsellor")
public class ReferralCounsellor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int referid;

	@Column(length = 30)
	private String username;

	@Column(length = 30)
	private String password;

	public ReferralCounsellor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getReferid() {
		return referid;
	}

	public void setReferid(int referid) {
		this.referid = referid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
