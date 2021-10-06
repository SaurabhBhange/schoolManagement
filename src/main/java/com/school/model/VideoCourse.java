package com.school.model;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "videocourse")
public class VideoCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int videoCourseId;

	@Column(length = 100)
	private String courseName;

	@Column(length = 50)
	private String price;

	@Lob
	private byte[] courseImage;

	@Lob
	private byte[] coursePDF;

	@Column(length = 100)
	private String ImageFileName;

	@Column(length = 100)
	private String pdfFileName;

	@JsonIgnore
	@OneToMany(mappedBy = "courseName", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<VideoUpload> videos;

	@JsonIgnore
	@OneToMany(mappedBy = "uservideo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<uservideomapping> uservideomapping;

	public VideoCourse() {
		super();
	}

	public VideoCourse(String courseName, String price, byte[] courseImage, byte[] coursePDF, String imageFileName,
			String pdfFileName) {
		super();
		this.courseName = courseName;
		this.price = price;
		this.courseImage = courseImage;
		this.coursePDF = coursePDF;
		ImageFileName = imageFileName;
		this.pdfFileName = pdfFileName;
	}

	public int getVideoCourseId() {
		return videoCourseId;
	}

	public void setVideoCourseId(int videoCourseId) {
		this.videoCourseId = videoCourseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public String getImageFileName() {
		return ImageFileName;
	}

	public void setImageFileName(String imageFileName) {
		ImageFileName = imageFileName;
	}

	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}

	public Set<VideoUpload> getVideos() {
		return videos;
	}

	public void setVideos(Set<VideoUpload> videos) {
		this.videos = videos;
	}

	public Set<uservideomapping> getUservideomapping() {
		return uservideomapping;
	}

	public void setUservideomapping(Set<uservideomapping> uservideomapping) {
		this.uservideomapping = uservideomapping;
	}

	@Override
	public String toString() {
		return "VideoCourse [videoCourseId=" + videoCourseId + ", courseName=" + courseName + ", price=" + price
				+ ", courseImage=" + Arrays.toString(courseImage) + ", coursePDF=" + Arrays.toString(coursePDF)
				+ ", ImageFileName=" + ImageFileName + ", pdfFileName=" + pdfFileName + "]";
	}

}
