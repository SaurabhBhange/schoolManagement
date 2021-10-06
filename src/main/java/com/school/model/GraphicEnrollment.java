package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GraphicEnrollment")
public class GraphicEnrollment{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;			
	@Column(length = 50)
	private String fullName;
	@Column(length = 35)
	private String email;
	@Column(length = 10)
	private String mobile;	
	@Column(length = 1000)
	private String graphicEnrollmentInterest;
	@Column
	private String created;
	@Column
	private String comment;
	@Column
	private String colour;	

	public GraphicEnrollment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GraphicEnrollment(String fullName, String email, String mobile, String graphicEnrollmentInterest,
			String created, String comment, String colour) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.mobile = mobile;
		this.graphicEnrollmentInterest = graphicEnrollmentInterest;
		this.created = created;
		this.comment = comment;
		this.colour = colour;
		}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGraphicEnrollmentInterest() {
		return graphicEnrollmentInterest;
	}
	public void setGraphicEnrollmentInterest(String graphicEnrollmentInterest) {
		this.graphicEnrollmentInterest = graphicEnrollmentInterest;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}


	@Override
	public String toString() {
		return "GraphicEnrollment [id=" + id + ", fullName=" + fullName + ", email=" + email + ", mobile=" + mobile
				+ ", graphicEnrollmentInterest=" + graphicEnrollmentInterest + ", created=" + created + ", comment="
				+ comment + ", colour=" + colour + "]";
	}

	

}
