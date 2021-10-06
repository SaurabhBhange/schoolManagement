package com.school.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class StudentAttendaceDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int userId;
	private String fname;
	private String lname;
	private String date;
	private String detail;
	private int course_id;
	private int batch_id;
	private int status;

	public StudentAttendaceDto() {
	}

	public StudentAttendaceDto(int userId, String fname, String lname, String date, String detail, int course_id,
			int batch_id, int status) {
		super();
		this.userId = userId;
		this.fname = fname;
		this.lname = lname;
		this.date = date;
		this.detail = detail;
		this.course_id = course_id;
		this.batch_id = batch_id;
		this.status = status;
	}

	@JsonProperty
	public int getUser_id() {
		return userId;
	}

	@JsonProperty
	public void setUser_id(int userId) {
		this.userId = userId;
	}

	@JsonProperty
	public String getFname() {
		return fname;
	}

	@JsonProperty
	public void setFname(String fname) {
		this.fname = fname;
	}

	@JsonProperty
	public String getLname() {
		return lname;
	}

	@JsonProperty
	public void setLname(String lname) {
		this.lname = lname;
	}

	@JsonProperty
	public String getDate() {
		return date;
	}

	@JsonProperty
	public void setDate(String date) {
		this.date = date;
	}

	@JsonProperty
	public String getDetail() {
		return detail;
	}

	@JsonProperty
	public void setDetail(String detail) {
		this.detail = detail;
	}

	@JsonProperty
	public int getStatus() {
		return status;
	}

	@JsonProperty
	public void setStatus(int status) {
		this.status = status;
	}

	@JsonProperty
	public int getCourse_id() {
		return course_id;
	}

	@JsonProperty
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	@JsonProperty
	public int getBatch_id() {
		return batch_id;
	}

	@JsonProperty
	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
	}

	@Override
	public String toString() {
		return "StudentAttendaceDto [userId=" + userId + ", fname=" + fname + ", lname=" + lname + ", date=" + date
				+ ", detail=" + detail + ", course_id=" + course_id + ", batch_id=" + batch_id + ", status=" + status
				+ "]";
	}

}
