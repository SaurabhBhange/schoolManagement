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
@Table(name = "webinarcourse")
public class WebinarCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int course_id;

	@Column(length = 100)
	private String courseName;

	@Column(length = 100, unique = false)
	private String duration;

	@Column(length = 100)
	private String imageFilename;

	@Lob
	@Column(length = 10000)
	private byte[] courseImage;

	private String date;

	private String time;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private WebinarCategory category;

	public WebinarCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebinarCourse(String courseName, String duration, String imageFilename, byte[] courseImage, String date,
			String time, WebinarCategory category) {
		super();
		this.courseName = courseName;
		this.duration = duration;
		this.imageFilename = imageFilename;
		this.courseImage = courseImage;
		this.date = date;
		this.time = time;
		this.category = category;
	}

	public WebinarCourse(String courseName, String duration, String imageFilename, byte[] courseImage, String date,String time) {
		super();
		this.courseName = courseName;
		this.duration = duration;
		this.imageFilename = imageFilename;
		this.courseImage = courseImage;
		this.date = date;
		this.time = time;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public WebinarCategory getCategory() {
		return category;
	}

	public void setCategory(WebinarCategory category) {
		this.category = category;
	}

	@JsonSerialize(using = WebCategorySerilizer.class)
	public WebinarCategory getCategories() {
		return category;
	}

	@Override
	public String toString() {
		return "WebinarCourse [course_id=" + course_id + ", courseName=" + courseName + ", duration=" + duration
				+ ", imageFilename=" + imageFilename + ", courseImage=" + Arrays.toString(courseImage) + ", date="
				+ date + ", time=" + time + ", category=" + category + "]";
	}

}
