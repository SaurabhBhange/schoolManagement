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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int course_id;
	@Column(length = 100)

	private String courseName;
	@Column(length = 100, unique = false)

	private String basicPlan;
	@Column(length = 32)
	private String premiumPlan;

	@Column(length = 100)
	private String imageFilename;

	@Column(length = 100)
	private String pdfFilename;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "userId") private StudentPojo studentPojo;
	 * 
	 * public StudentPojo getStudentPojo() { return studentPojo; }
	 * 
	 * public void setStudentPojo(StudentPojo studentPojo) { this.studentPojo =
	 * studentPojo; }
	 */

	@Lob
	@Column(length = 10000, nullable = true)
	private byte[] courseImage;

	@Lob
	@Column(length = 10000, nullable = true)
	private byte[] coursePDF;

//	@JsonIgnore
//	@OneToMany(mappedBy = "batch", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private Set<StudentCourseBatch> studentCourseBatch;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER) // ,cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;

	public Course(String courseName, String basicPlan, String premiumPlan, byte[] courseImage, byte[] coursePDF,
			Category category) {
		super();
		this.courseName = courseName;
		this.basicPlan = basicPlan;
		this.premiumPlan = premiumPlan;
		this.courseImage = courseImage;
		this.coursePDF = coursePDF;
		this.category = category;
	}

	public Course(String courseName, String basicPlan, String premiumPlan, String imageFilename, String pdfFilename,
			byte[] courseImage, byte[] coursePDF) {
		super();
		this.courseName = courseName;
		this.basicPlan = basicPlan;
		this.premiumPlan = premiumPlan;
		this.imageFilename = imageFilename;
		this.pdfFilename = pdfFilename;
		this.courseImage = courseImage;
		this.coursePDF = coursePDF;
	}

	/*
	 * public Course(String courseName, String basicPlan,String premiumPlan, byte[]
	 * courseImage, byte[] coursePDF) { super(); this.courseName = courseName;
	 * this.basicPlan=basicPlan; this.premiumPlan=premiumPlan; this.courseImage =
	 * courseImage; this.coursePDF = coursePDF;
	 * 
	 * // this.category=category; }
	 */

	public Course() {
		// TODO Auto-generated constructor stub
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/*
	 * @Override public String toString() { return "Course [course_id=" + course_id
	 * + ", courseName=" + courseName + ", basicPlan=" + basicPlan +
	 * ", premiumPlan=" + premiumPlan + ", courseImage=" +
	 * Arrays.toString(courseImage) + ", coursePDF=" + Arrays.toString(coursePDF)
	 * +", category=" + category + "]"; }
	 */

	@JsonSerialize(using = CategorySerilizer.class)
	public Category getCategories() {
		return category;
	}

	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", courseName=" + courseName + ", basicPlan=" + basicPlan
				+ ", premiumPlan=" + premiumPlan + ", imageFilename=" + imageFilename + ", pdfFilename=" + pdfFilename
				+ ", courseImage=" + Arrays.toString(courseImage) + ", coursePDF=" + Arrays.toString(coursePDF)
				+ ", category=" + category + "]";
	}

}
