package com.school.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bucketCertificate")
public class bucketCertificate {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length=50)
	private String fullName;
	
	@Column(length=10)
	private String issueDate;
	
	@Column(length=20)
	private String courseName;
	
	private byte[] img;
	
	private String imgName;

	public bucketCertificate() {
		super();
	}

	public bucketCertificate(String fullName, String issueDate, String courseName, byte[] img, String imgName) {
		super();
		this.fullName = fullName;
		this.issueDate = issueDate;
		this.courseName = courseName;
		this.img = img;
		this.imgName = imgName;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	@Override
	public String toString() {
		return "bucketCertificate [fullName=" + fullName + ", issueDate=" + issueDate + ", courseName=" + courseName
				+ ", img=" + Arrays.toString(img) + ", imgName=" + imgName + "]";
	}
	
}
