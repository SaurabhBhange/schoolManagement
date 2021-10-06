package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Designation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int designation_id;
	
	@Column(length=100)
	private String designationName;

	public Designation() {
		super();
	}

	public Designation(String designationName) {
		super();
		this.designationName = designationName;
	}

	public int getDesignation_id() {
		return designation_id;
	}

	public void setDesignation_id(int designation_id) {
		this.designation_id = designation_id;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	@Override
	public String toString() {
		return "Designation [designation_id=" + designation_id + ", designationName=" + designationName + "]";
	}
}