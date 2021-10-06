package com.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.Employment;

public interface EmploymentRepo extends JpaRepository<Employment, Integer> {

	Page<Employment> findAllByOrderByHiringIdDesc(Pageable paging);

	/*
	 * @Modifying
	 * 
	 * @Transactional // Make sure to import
	 * org.springframework.transaction.annotation.Transactional public void
	 * deleteByCreatedOnBefore(Date expiryDate);
	 */
}
