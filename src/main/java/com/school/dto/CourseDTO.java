package com.school.dto;

public class CourseDTO {

	private int course_id;

	private String courseName;

	private String basicPlan;
	private String premiumPlan;
	private byte[] courseImage;

	private byte[] coursePDF;
	
	private int category_id;
	
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
	public byte[] getCourseImage() {
		return courseImage;
	}
	public void setCourseImage(byte[] courseImage) {
		this.courseImage = courseImage;
	}
	public byte[] getCoursePDF() {
		return coursePDF;
	}
	public void setCoursePDF(byte[] coursePDF) {
		this.coursePDF = coursePDF;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	

}
