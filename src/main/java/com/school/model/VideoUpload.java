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

@Entity
@Table(name = "VideoUpload")
public class VideoUpload {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int videoId;

	@Column(length = 100)
	private String title;

	@Column(length = 200)
	private String description;

	@Lob
	@Column
	private byte[] thumbnail;

	@Column(length = 200)
	private String thumbnailName;

	@Lob
	@Column
	private byte[] video;

	@Column(length = 200)
	private String videoFilename;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "videoCourseId")
	private VideoCourse courseName;

	public VideoUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VideoUpload(String title, String description, byte[] thumbnail, String thumbnailName, byte[] video,
			String videoFilename) {
		super();
		this.title = title;
		this.description = description;
		this.thumbnail = thumbnail;
		this.thumbnailName = thumbnailName;
		this.video = video;
		this.videoFilename = videoFilename;
	}

	public VideoUpload(String title, String description, byte[] thumbnail, byte[] video, VideoCourse courseName) {
		super();
		this.title = title;
		this.description = description;
		this.thumbnail = thumbnail;
		this.video = video;
		this.courseName = courseName;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public byte[] getVideo() {
		return video;
	}

	public void setVideo(byte[] video) {
		this.video = video;
	}

	public String getVideoFilename() {
		return videoFilename;
	}

	public void setVideoFilename(String videoFilename) {
		this.videoFilename = videoFilename;
	}

	public String getThumbnailName() {
		return thumbnailName;
	}

	public void setThumbnailName(String thumbnailName) {
		this.thumbnailName = thumbnailName;
	}

	public VideoCourse getCourseName() {
		return courseName;
	}

	public void setCourseName(VideoCourse courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "VideoUpload [videoId=" + videoId + ", title=" + title + ", description=" + description + ", thumbnail="
				+ Arrays.toString(thumbnail) + ", thumbnailName=" + thumbnailName + ", video=" + Arrays.toString(video)
				+ ", videoFilename=" + videoFilename + "]";
	}

}
