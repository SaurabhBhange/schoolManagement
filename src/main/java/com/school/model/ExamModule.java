package com.school.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "exammodule")
public class ExamModule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int examId;

	@Column(length = 100)
	private String examName;

	@Column(length = 100)
	private String duration;

	@Column(length = 50)
	private String passingPercentage;

	@Column(length = 100)
	private Integer course_id;

	@Column(length = 100)
	private Integer batch_id;

	@Column(length = 100)
	private String courseName;

	@Column(length = 100)
	private String batchName;

	@Column(name = "status", nullable = false)
	private int Status = 0;

	@JsonIgnore
	@OneToMany(mappedBy = "examModule", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<QuestionModel> questions;

	@JsonIgnore
	@OneToMany(mappedBy = "exammodule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ExamStudentMapping> examstudentmapping;

	public ExamModule() {
		super();
	}

	public ExamModule(String examName, String duration, String passingPercentage, Integer course_id, Integer batch_id,
			String courseName, String batchName, Set<QuestionModel> questions,
			Set<ExamStudentMapping> examstudentmapping) {
		super();
		this.examName = examName;
		this.duration = duration;
		this.passingPercentage = passingPercentage;
		this.course_id = course_id;
		this.batch_id = batch_id;
		this.courseName = courseName;
		this.batchName = batchName;
		this.questions = questions;
		this.examstudentmapping = examstudentmapping;
	}

	public ExamModule(int examId, String examName, String duration, String passingPercentage, Integer course_id,
			Integer batch_id, String courseName, String batchName) {
		super();
		this.examId = examId;
		this.examName = examName;
		this.duration = duration;
		this.passingPercentage = passingPercentage;
		this.course_id = course_id;
		this.batch_id = batch_id;
		this.courseName = courseName;
		this.batchName = batchName;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPassingPercentage() {
		return passingPercentage;
	}

	public void setPassingPercentage(String passingPercentage) {
		this.passingPercentage = passingPercentage;
	}

	public Integer getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	public Integer getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(Integer batch_id) {
		this.batch_id = batch_id;
	}

	public Set<QuestionModel> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<QuestionModel> questions) {
		this.questions = questions;
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

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "ExamModule [examId=" + examId + ", examName=" + examName + ", duration=" + duration
				+ ", passingPercentage=" + passingPercentage + ", course_id=" + course_id + ", batch_id=" + batch_id
				+ ", courseName=" + courseName + ", batchName=" + batchName + ", Status=" + Status + ", questions="
				+ questions + ", examstudentmapping=" + examstudentmapping + "]";
	}

}
