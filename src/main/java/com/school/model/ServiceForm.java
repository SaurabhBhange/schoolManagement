package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServiceForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serviceId;
	
	@Column(length=32)
	private String fullName;
	@Column(length=32)
	private String email;
	@Column(length=10)
	private String mobile;
	@Column(length=500)
	private String service;
	
	public ServiceForm() {
		super();
	}

public ServiceForm(String fullName, String email, String mobile, String service) {
	super();
	this.fullName = fullName;
	this.email = email;
	this.mobile = mobile;
	this.service= service;
}

	public int getService_id() {
		return serviceId;
	}

	public void setService_id(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getService() {
		return service;
	}

	public void setServiceName(String service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "ServiceForm [serviceId=" + serviceId + ", fullName=" + fullName + ", email=" + email + ", mobile="
				+ mobile + ", service=" + service + "]";
	}


}
