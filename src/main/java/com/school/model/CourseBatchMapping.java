package com.school.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "coursebatchmapping")
public class CourseBatchMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 100)
	private int course_id;

	@Column(length = 100)
	private int batch_id;

	@Column(length = 100)
	private String courseName;

	@Column(length = 100)
	private String batchName;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private StudentPojo studenttbl;

	public int getCourse_id() {
		return course_id;
	}

	public CourseBatchMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public CourseBatchMapping(int id, int course_id, int batch_id, String courseName, String batchName) {
		super();
		this.id = id;
		this.course_id = course_id;
		this.batch_id = batch_id;
		this.courseName = courseName;
		this.batchName = batchName;
	}

	public CourseBatchMapping(int course_id, int batch_id, String courseName, String batchName,
			StudentPojo studenttbl) {
		super();
		this.course_id = course_id;
		this.batch_id = batch_id;
		this.courseName = courseName;
		this.batchName = batchName;
		this.studenttbl = studenttbl;
	}
	
	

	public CourseBatchMapping(int course_id, int batch_id, StudentPojo studenttbl) {
		super();
		this.course_id = course_id;
		this.batch_id = batch_id;
		this.studenttbl = studenttbl;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
	}

	public StudentPojo getStudenttbl() {
		return studenttbl;
	}

	public void setStudenttbl(StudentPojo studenttbl) {
		this.studenttbl = studenttbl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	
	

}
