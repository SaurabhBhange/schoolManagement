package com.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "results")
public class ResultPojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private int totalCorrect = 0;

	private int totalWrong;

	private int totalQuestions;

	private String result;

	private int examId;

	public ResultPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalWrong() {
		return totalWrong;
	}

	public void setTotalWrong(int totalWrong) {
		this.totalWrong = totalWrong;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public ResultPojo(String username, int totalCorrect, int totalWrong, int totalQuestions, String result,
			int examId) {
		super();
		this.username = username;
		this.totalCorrect = totalCorrect;
		this.totalWrong = totalWrong;
		this.totalQuestions = totalQuestions;
		this.result = result;
		this.examId = examId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTotalCorrect() {
		return totalCorrect;
	}

	public void setTotalCorrect(int totalCorrect) {
		this.totalCorrect = totalCorrect;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	@Override
	public String toString() {
		return "ResultPojo [id=" + id + ", username=" + username + ", totalCorrect=" + totalCorrect + ", totalWrong="
				+ totalWrong + ", totalQuestions=" + totalQuestions + ", result=" + result + ", examId=" + examId + "]";
	}

}
