package com.school.dto;

import java.io.Serializable;

public class CourseUpdateDTO implements Serializable {

	private int course_id;
	private String courseName;
	private String basicPlan;
	private String premiumPlan;
	private String courseImage;
	private String coursePDF;
	private String imageFilename;
	private String pdfFilename;
	private String category;
	private int cat_id;

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

	public String getBasicPlan() {
		return basicPlan;
	}

	public void setBasicPlan(String basicPlan) {
		this.basicPlan = basicPlan;
	}

	public String getPremiumPlan() {
		return premiumPlan;
	}

	public void setPremiumPlan(String premiumPlan) {
		this.premiumPlan = premiumPlan;
	}

	public String getCourseImage() {
		return courseImage;
	}

	public void setCourseImage(String courseImage) {
		this.courseImage = courseImage;
	}

	public String getCoursePDF() {
		return coursePDF;
	}

	public void setCoursePDF(String coursePDF) {
		this.coursePDF = coursePDF;
	}

	public String getImageFilename() {
		return imageFilename;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}

	public String getPdfFilename() {
		return pdfFilename;
	}

	public void setPdfFilename(String pdfFilename) {
		this.pdfFilename = pdfFilename;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public CourseUpdateDTO(int course_id, String courseName, String basicPlan, String premiumPlan, String courseImage,
			String coursePDF, String imageFilename, String pdfFilename, String category, int cat_id) {
		super();
		this.course_id = course_id;
		this.courseName = courseName;
		this.basicPlan = basicPlan;
		this.premiumPlan = premiumPlan;
		this.courseImage = courseImage;
		this.coursePDF = coursePDF;
		this.imageFilename = imageFilename;
		this.pdfFilename = pdfFilename;
		this.category = category;
		this.cat_id = cat_id;
	}

	public CourseUpdateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
