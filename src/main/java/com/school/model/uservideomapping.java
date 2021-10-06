package com.school.model;

import javax.persistence.CascadeType;
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
@Table(name = "uservideo")
public class uservideomapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "videoCourseId")
	private VideoCourse uservideo;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private StudentPojo studenttbl;

	
	public uservideomapping(VideoCourse uservideo, StudentPojo studenttbl) {
		super();
		this.uservideo = uservideo;
		this.studenttbl = studenttbl;
	}

	public uservideomapping() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VideoCourse getUservideo() {
		return uservideo;
	}

	public void setUservideo(VideoCourse uservideo) {
		this.uservideo = uservideo;
	}

	public StudentPojo getStudenttbl() {
		return studenttbl;
	}

	public void setStudenttbl(StudentPojo studenttbl) {
		this.studenttbl = studenttbl;
	}

	@Override
	public String toString() {
		return "uservideomapping [id=" + id + ", uservideo=" + uservideo + ", studenttbl=" + studenttbl + "]";
	}


}
