package com.school.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Employment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hiringId;
	@Column(length=32)
	private String fullName;
	@Column(length=32)
	private String email;
	@Column(length=10)
	private String phoneNo;
	@Column(length=50)
	private String message;
	@Lob
	@Column(length=10000)
	private byte[] resume;
	
	@Column(length=100)
	private String designation;
	
	@Column(length=100)
	private String filename;
	
	
	public Employment() {
		super();
	}

	public Employment(String fullName, String email, String phoneNo, String message, byte[] resume, String designation,String filename) {
	super();
	this.fullName = fullName;
	this.email = email;
	this.phoneNo = phoneNo;
	this.message = message;
	this.resume = resume;
	this.designation = designation;
	this.filename = filename;
}


	public int gethiringId() {
		return hiringId;
	}

	public void sethiringId(int hiringId) {
		this.hiringId = hiringId;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public byte[] getResume() {
		return resume;
	}

	public void setResume(byte[] resume) {
		this.resume = resume;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}

public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}
@Override
	public String toString() {
		return "Employment [hiringId=" + hiringId + ", fullName=" + fullName + ", email=" + email + ", phoneNo="
				+ phoneNo + ", message=" + message + ", resume=" + Arrays.toString(resume) + ", designation="
				+ designation + ", filename=" + filename + "]";
	}






}
