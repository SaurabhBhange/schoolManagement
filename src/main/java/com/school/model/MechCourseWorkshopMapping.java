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
@Table(name = "itmechcourseworkshopmapping")
public class MechCourseWorkshopMapping {

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
	private MechManyCourseWorkshop mechworkshops;

	public MechCourseWorkshopMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MechCourseWorkshopMapping(String label, int value, MechManyCourseWorkshop mechworkshops) {
		super();
		this.label = label;
		this.value = value;
		this.mechworkshops = mechworkshops;
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

	public MechManyCourseWorkshop getMechworkshops() {
		return mechworkshops;
	}

	public void setMechworkshops(MechManyCourseWorkshop mechworkshops) {
		this.mechworkshops = mechworkshops;
	}

	@Override
	public String toString() {
		return "MechCourseWorkshopMapping [id=" + id + ", label=" + label + ", value=" + value + ", mechworkshops="
				+ mechworkshops + "]";
	}

}
