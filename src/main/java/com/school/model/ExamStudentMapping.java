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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "examstudentmapping")
@Getter
@Setter
public class ExamStudentMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private StudentPojo studenttbl;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ExamId")
	private ExamModule exammodule;

	public ExamStudentMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExamStudentMapping(StudentPojo studenttbl, ExamModule exammodule) {
		super();
		this.studenttbl = studenttbl;
		this.exammodule = exammodule;
	}

	@Override
	public String toString() {
		return "ExamStudentMapping [id=" + id + ", studenttbl=" + studenttbl + ", exammodule=" + exammodule + "]";
	}

}
