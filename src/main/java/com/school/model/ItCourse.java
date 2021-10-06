
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
	public class ItCourse {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int course_id;
		@Column(length = 100)
		private String courseName;
		@Column(length = 1000, unique = false)
		private String basicPlan;
		@Column(length=32)
		private String premiumPlan;
		
		@Lob
		@Column(length = 10000)
		private byte[] courseImage;
		
		@Lob
		@Column(length = 10000)
		private byte[] coursePDF;
		
		@Column(length=100)
		private String itFileImage;
		
		@Column(length=100)
		private String itFilePdf;

	//	@JsonIgnore
	//	@OneToMany(mappedBy = "batch", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//	private Set<StudentCourseBatch> studentCourseBatch;
		
		@JsonIgnore
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name ="category_id")
		private ItCategory category;
		
		public ItCourse() {
			super();
		}

	
		public ItCourse(String courseName, String basicPlan, String premiumPlan, byte[] courseImage, byte[] coursePDF,
				String itFileImage, String itFilePdf) {
			super();
			this.courseName = courseName;
			this.basicPlan = basicPlan;
			this.premiumPlan = premiumPlan;
			this.courseImage = courseImage;
			this.coursePDF = coursePDF;
			this.itFileImage = itFileImage;
			this.itFilePdf = itFilePdf;
		}
		
		

		public ItCourse(String courseName, String basicPlan, String premiumPlan, byte[] courseImage, byte[] coursePDF,
				String itFileImage, String itFilePdf, ItCategory category) {
			super();
			this.courseName = courseName;
			this.basicPlan = basicPlan;
			this.premiumPlan = premiumPlan;
			this.courseImage = courseImage;
			this.coursePDF = coursePDF;
			this.itFileImage = itFileImage;
			this.itFilePdf = itFilePdf;
			this.category = category;
		}



		public String getItFileImage() {
			return itFileImage;
		}

		public void setItFileImage(String itFileImage) {
			this.itFileImage = itFileImage;
		}

		public String getItFilePdf() {
			return itFilePdf;
		}

		public void setItFilePdf(String itFilePdf) {
			this.itFilePdf = itFilePdf;
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
		
	
		public ItCategory getCategory() {
			return category;
		}

		public void setCategory(ItCategory category) {
			this.category = category;
		}

	/*	@Override
		public String toString() {
			return "Course [courseName=" + courseName + ",basicPlan=" + basicPlan
					+",premiumPlan="+premiumPlan+ ", courseImage=" + Arrays.toString(courseImage) + ", coursePDF=" + Arrays.toString(coursePDF) + ", category=" + category+"]";
		}
		*/
		
		
		@JsonSerialize(using = ItCategorySerilizer.class)
		public ItCategory getCategories() {
		    return category;
		}

		@Override
		public String toString() {
			return "ItCourse [courseName=" + courseName + ", basicPlan=" + basicPlan + ", premiumPlan=" + premiumPlan
					+ ", courseImage=" + Arrays.toString(courseImage) + ", coursePDF=" + Arrays.toString(coursePDF)
					+ ", itFileImage=" + itFileImage + ", itFilePdf=" + itFilePdf + ", category=" + category + "]";
		}	
	}


