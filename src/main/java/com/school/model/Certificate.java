package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Certificate {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Id
	@Column(length = 8)
	private String certificateNumber;
	@Column(length = 100)
	private String fullName;
	@Column(length = 12)
	private String startDate;
	@Column(length = 12)
	private String endDate;
	@Column
	private String course;

	public Certificate() {
		super();
	}

	public Certificate(String certificateNumber, String fullName, String startDate, String endDate, String course) {
		super();
		this.certificateNumber = certificateNumber;
		this.fullName = fullName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.course = course;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Certificate [id=" + id + ", certificateNumber=" + certificateNumber + ", fullName=" + fullName
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", course=" + course + "]";
	}

}
