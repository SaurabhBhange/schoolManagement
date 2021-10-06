package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.BatchPojo;

public interface BatchRepo extends JpaRepository<BatchPojo, Integer> {

	public BatchPojo findByBatch(String batch);

}
