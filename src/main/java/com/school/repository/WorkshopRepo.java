package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.Workshop;

public interface WorkshopRepo extends JpaRepository<Workshop, Integer> {
	

	
}
