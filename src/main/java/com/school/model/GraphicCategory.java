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
@Table(name="graphic_category")
public class GraphicCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cat_id;
	@Column(length = 40)
	private String category;

	@JsonIgnore
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<GraphicCourse> courses;

	public GraphicCategory() {
		super();
	}

	
public GraphicCategory(String category) {
		super();
		this.category = category;
	}


//graphicCategory
	
	public GraphicCategory(int cat_id, String category) {
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

	public Set<GraphicCourse> getCourses() {
		return courses;
	}

	public void setCourses(Set<GraphicCourse> courses) {
		this.courses = courses;
	}


	@Override
	public String toString() {
		return "GraphicCategory [cat_id=" + cat_id + ", category=" + category + "]";
	}

}
