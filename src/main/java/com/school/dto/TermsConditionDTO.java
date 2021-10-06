package com.school.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TermsConditionDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int terms_id;
	@Column(length=50)
	private String full_name;
	@Column(length=50)
	private String email;
	
	public TermsConditionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TermsConditionDTO(String full_name, String email) {
		super();
		this.full_name = full_name;
		this.email = email;
	}

	public int getTerms_id() {
		return terms_id;
	}

	public void setTerms_id(int terms_id) {
		this.terms_id = terms_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "TermsConditionDTO [terms_id=" + terms_id + ", full_name=" + full_name + ", email=" + email + "]";
	}
	
}
