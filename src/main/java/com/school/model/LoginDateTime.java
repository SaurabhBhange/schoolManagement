package com.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoginDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String date;
	
	private String loginTime;
	
	private String logoutTime;

	public LoginDateTime() {
		super();
	}

	public LoginDateTime(String firstName, String lastName, String date, String loginTime, String logoutTime) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.date = date;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Override
	public String toString() {
		return "LoginDateTime [firstname=" + firstName + ", lastname=" + lastName + ", date=" + date + ", loginTime="
				+ loginTime + ", logoutTime=" + logoutTime + "]";
	}
	
	
	
}