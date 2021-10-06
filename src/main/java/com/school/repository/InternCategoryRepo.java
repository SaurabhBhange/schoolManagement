package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.InternshipCategory;

public interface InternCategoryRepo extends JpaRepository<InternshipCategory, Integer> {

	public InternshipCategory findByCategory(String category);

}
