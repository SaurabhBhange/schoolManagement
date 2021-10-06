//package com.school.model;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "studentattendancedetails")
//public class StudentAttendanceDetails implements Serializable {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int sadetails_id;
//
//	private int user_id;
//
//	private int course_id;
//
//	private int batch_id;
//
//	private int status;
//
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "comment_id", referencedColumnName = "id")
//	private List<StudentAttendanceDetails> studentAttendanceDetails;
//	/// comment_id--id
//*/
///*	public StudentAttendanceDetails() {
//		super();
//
//	}
//
//	public StudentAttendanceDetails(int sadetails_id, int user_id, int course_id, int batch_id, int status) {
//		super();
//		this.sadetails_id = sadetails_id;
//		this.user_id = user_id;
//		this.course_id = course_id;
//		this.batch_id = batch_id;
//		this.status = status;
//	}
//
//	public int getSadetails_id() {
//		return sadetails_id;
//	}
//
//	public void setSadetails_id(int sadetails_id) {
//		this.sadetails_id = sadetails_id;
//	}
//
//	public int getUser_id() {
//		return user_id;
//	}
//
//	public void setUser_id(int user_id) {
//		this.user_id = user_id;
//	}
//
//	public int getStatus() {
//		return status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}
//
//	public int getCourse_id() {
//		return course_id;
//	}
//
//	public void setCourse_id(int course_id) {
//		this.course_id = course_id;
//	}
//
//	public int getBatch_id() {
//		return batch_id;
//	}
//
//	public void setBatch_id(int batch_id) {
//		this.batch_id = batch_id;
//	}
//
//
//	@Override
//	public String toString() {
//		return "StudentAttendanceDetails [sadetails_id=" + sadetails_id + ", user_id=" + user_id + ", course_id="
//				+ course_id + ", batch_id=" + batch_id + ", status=" + status + "]";
//	}
//	}
//*/
//
