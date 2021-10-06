package com.school.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentDto {

	private Integer userId;

	private String firstName;

	private String lastName;

	private String course_id;

	public StudentDto() {
	}

	@JsonCreator
	public StudentDto(@JsonProperty("userId") Integer userId, @JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String last_name,@JsonProperty("course_id")int course_id	 ) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;	
	}

	public Integer getuserId() {
		return userId;
	}

	public void setuserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

}
