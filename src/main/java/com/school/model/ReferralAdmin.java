package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "referraladmin")
public class ReferralAdmin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int referadminid;

	@Column(length = 30)
	private String username;

	@Column(length = 30)
	private String password;

	public ReferralAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getReferadminid() {
		return referadminid;
	}

	public void setReferadminid(int referadminid) {
		this.referadminid = referadminid;
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
