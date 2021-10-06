package com.school.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="webinarcategory")
public class WebinarCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cat_id;
	@Column(length = 40)
	private String category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category",orphanRemoval = true,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<WebinarCourse> courses;
	
	public WebinarCategory() {
		super();
	// TODO Auto-generated constructor stub
	}
		
	public WebinarCategory(int cat_id, String category) {
		super();
		this.cat_id = cat_id;
		this.category = category;
	}
	
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}	
	
	public Set<WebinarCourse> getCourses() {
		return courses;
	}

	public void setCourses(Set<WebinarCourse> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "WebinarCategory [cat_id=" + cat_id + ", category=" + category + "]";
	}
	
	
	
}
