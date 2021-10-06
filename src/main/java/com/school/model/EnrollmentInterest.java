
package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enrollmentInterest")
public class EnrollmentInterest {
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
	private String interest;
	@Column
	private String comment;
	@Column
	private String colour;
	@Column
	private String created;

	public EnrollmentInterest() {
		super();
	}

	public EnrollmentInterest(String fullName, String email, String mobile, String interest, String comment,
			String colour, String created) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.mobile = mobile;
		this.interest = interest;
		this.comment = comment;
		this.colour = colour;
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

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "EnrollmentInterest [id=" + id + ", fullName=" + fullName + ", email=" + email + ", mobile=" + mobile
				+ ", interest=" + interest + ", comment=" + comment + ", colour=" + colour + ", created=" + created
				+ "]";
	}
}
