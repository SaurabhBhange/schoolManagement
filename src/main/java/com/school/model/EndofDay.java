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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="eod")
public class EndofDay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",unique = true, nullable = false)
	private int id;
	
	@Column(length=10)
	private String Date;
	
	@Column(length=10)
	private String time;
		
	private String task;
	
	@Column(length=50)
	private String remark;
	
    String user;
    

	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="username_id")
	private UserPojo username;

	public EndofDay() {
		super();
	}

	
	
	public EndofDay( String date, String time, String task, String remark) {
		super();
		Date = date;
		this.time = time;
		this.task = task;
		this.remark = remark;
	}



	public EndofDay(String date, String time, String task, String remark, UserPojo username) {
		super();
		Date = date;
		this.time = time;
		this.task = task;
		this.remark = remark;
		this.username = username;
	}
	



	public UserPojo getUsername() {
		return username;
	}



	public void setUsername(UserPojo username) {
		this.username = username;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



	@Override
	public String toString() {
		return "EndofDay [id=" + id + ", Date=" + Date + ", time=" + time + ", task=" + task + ", remark=" + remark
				+ ", username=" + username + "]";
	}
	
	@JsonSerialize(using = UserPojoSerilizer.class)
	public UserPojo getUserPojo() {
	    return username;
	}
	
	
}
