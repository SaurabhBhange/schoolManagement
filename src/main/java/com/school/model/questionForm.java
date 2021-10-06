package com.school.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class questionForm {
	
	private List<QuestionModel> qform;

	public List<QuestionModel> getQform() {
		return qform;
	}

	public void setQform(List<QuestionModel> qform) {
		this.qform = qform;
	}

}
