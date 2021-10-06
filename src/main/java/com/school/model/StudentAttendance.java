package com.school.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "studentattendancemapping")
public class StudentAttendance implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String date;

	private String detail;

//	@JsonIgnore
//	@OneToMany(mappedBy = "id", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<StudentAttendanceDetails> studentAttendanceDetails;

	// @OneToMany(cascade = CascadeType.ALL)
	// @JoinColumn(name = "sad_id", referencedColumnName = "id")
	// private List<StudentAttendanceDetails> studentAttendanceDetails;

	public StudentAttendance() {
		super();
	}

	public StudentAttendance(int id, String date, String detail) {
		super();
		this.id = id;
		this.date = date;
		this.detail = detail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "StudentAttendance [id=" + id + ", date=" + date + ", detail=" + detail + "]";
	}

}