package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.Designation;

public interface DesignationRepo extends JpaRepository<Designation, Integer> {

	Designation findByDesignationName(String designation);

}
