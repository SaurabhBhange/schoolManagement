package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contactus")
public class Contactus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contactId;
	@Column(length = 100)
	private String fullName;
	@Column(length = 100, unique = true)
	private String email;
	@Column(length = 10)
	private String mobileNumber;
	@Column(length = 1000)
	private String message;
	@Column(name = "created_on")
	private String createdOn;

	public Contactus() {
		super();
	}

	public Contactus(String fullName, String email, String mobileNumber, String message, String createdOn) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.message = message;
		this.createdOn = createdOn;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public int getcontactId() {
		return contactId;
	}

	public void setcontactId(int contactId) {
		this.contactId = contactId;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Contactus [contactId=" + contactId + ", fullName=" + fullName + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", message=" + message + ", createdOn=" + createdOn + "]";
	}

}
