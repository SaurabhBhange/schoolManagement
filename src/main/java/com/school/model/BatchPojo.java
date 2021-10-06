package com.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "batch")
public class BatchPojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int batch_id;
	@Column(length = 32, unique = true)
	private String batch;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "batch", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private Set<StudentCourseBatch> studentCourseBatch;
	
	public BatchPojo() {
		super();
	}
	public BatchPojo(String batch) {
		super();
		this.batch = batch;
	}
	public int getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	
}
