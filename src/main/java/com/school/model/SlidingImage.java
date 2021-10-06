package com.school.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class SlidingImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 200)
	private String Title;

	@Column(length = 500)
	private String Description;

	@Lob
	@Column
	private byte[] image;

	@Column(length = 100)
	private String fileName;

	public SlidingImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SlidingImage(String title, String description, byte[] image, String fileName) {
		super();
		Title = title;
		Description = description;
		this.image = image;
		this.fileName = fileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public String toString() {
		return "SlidingImage [id=" + id + ", Title=" + Title + ", Description=" + Description + ", image="
				+ Arrays.toString(image) + ", fileName=" + fileName + "]";
	}

}
