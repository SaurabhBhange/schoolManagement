package com.school.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="internship_course")
public class InternshipCourse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int course_id;
	
	@Column(length = 100,nullable = false)
	private String courseName;
	
	@Column(length = 100, unique = false)
	private String duration;
	
	@Column(length=100)
	private String imageFilename;
		
	@Lob
	@Column(length = 10000)
	private byte[] courseImage;
	

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="cat_id")
	private InternshipCategory category;


	public InternshipCourse() {
		super();
		// TODO Auto-generated constructor stub
	}



	public InternshipCourse(String courseName, String duration, String imageFilename, byte[] courseImage,
			InternshipCategory category) {
		super();
		this.courseName = courseName;
		this.duration = duration;
		this.imageFilename = imageFilename;
		this.courseImage = courseImage;
		this.category = category;
	}

	public InternshipCourse(String courseName, String duration, String imageFilename, byte[] courseImage) {
		super();
		this.courseName = courseName;
		this.duration = duration;
		this.imageFilename = imageFilename;
		this.courseImage = courseImage;
	}


	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getImageFilename() {
		return imageFilename;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}

	public byte[] getCourseImage() {
		return courseImage;
	}
	
	public void setCourseImage(byte[] courseImage) {
		this.courseImage = courseImage;
	}
	
	public InternshipCategory getCategory() {
		return category;
	}


	public void setCategory(InternshipCategory category) {
		this.category = category;
	}

	
	@Override
	public String toString() {
		return "InternshipCourse [course_id=" + course_id + ", courseName=" + courseName + ", duration=" + duration
				+ ", imageFilename=" + imageFilename + ", courseImage=" + Arrays.toString(courseImage) + ", category="
				+ category + "]";
	}


	@JsonSerialize(using = InternCategorySerlizer.class)
	public InternshipCategory getCategories() {
	    return category;
	}

	
	
}
