package com.school.dto;

import java.util.List;

import com.school.model.ExamModule;
import com.school.model.StudentPojo;

public class ExamStudentMappingRequest {
	
	
	private List<StudentPojo> student;
	private List<ExamModule> exam;
	
	
	public List<StudentPojo> getStudent() {
		return student;
	}
	public void setStudent(List<StudentPojo> student) {
		this.student = student;
	}
	public List<ExamModule> getExam() {
		return exam;
	}
	public void setExam(List<ExamModule> exam) {
		this.exam = exam;
	}
	
	

}
