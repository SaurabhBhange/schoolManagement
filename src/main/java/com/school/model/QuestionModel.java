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
@Table(name = "question_pojo")
public class QuestionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;

	@Column
	private String title;

	@Column()
	private String optionA;
	@Column()
	private String optionB;
	@Column()
	private String optionC;
	@Column()
	private String optionD;

	@Column
	private int ans;

	@Column
	private int choose;

	@Column(length = 10)
	private String mark;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "examId")
	private ExamModule examModule;

	public QuestionModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionModel(String title, String optionA, String optionB, String optionC, String optionD, int ans,
			int choose, String mark, ExamModule examModule) {
		super();
		this.title = title;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.ans = ans;
		this.choose = choose;
		this.mark = mark;
		this.examModule = examModule;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public ExamModule getExamModule() {
		return examModule;
	}

	public void setExamModule(ExamModule examModule) {
		this.examModule = examModule;
	}

	public int getAns() {
		return ans;
	}
	
	public void setAns(int ans) {
		this.ans = ans;
	}

	public int getChoose() {
		return choose;
	}

	public void setChoose(int choose) {
		this.choose = choose;
	}

	@Override
	public String toString() {
		return "QuestionModel [questionId=" + questionId + ", title=" + title + ", optionA=" + optionA + ", optionB="
				+ optionB + ", optionC=" + optionC + ", optionD=" + optionD + ", ans=" + ans + ", choose=" + choose
				+ ", mark=" + mark + ", examModule=" + examModule + "]";
	}

}
