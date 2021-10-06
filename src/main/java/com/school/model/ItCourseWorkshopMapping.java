package com.school.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "itcourseworkshop")
public class ItCourseWorkshopMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "coursename")
	private String label;

	@Column(name = "courseid")
	private int value;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "workshopid")
	private ItManyCourseWorkshop itworkshops;

	public ItCourseWorkshopMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItCourseWorkshopMapping(String label, int value, ItManyCourseWorkshop itworkshops) {
		super();
		this.label = label;
		this.value = value;
		this.itworkshops = itworkshops;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ItManyCourseWorkshop getItworkshops() {
		return itworkshops;
	}

	public void setItworkshops(ItManyCourseWorkshop itworkshops) {
		this.itworkshops = itworkshops;
	}

	@Override
	public String toString() {
		return "ItCourseWorkshopMapping [label=" + label + ", value=" + value + ", itworkshops=" + itworkshops + "]";
	}

}
