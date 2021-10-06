package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.ItCategory;

public interface ItCategoryRepo extends JpaRepository<ItCategory, Integer> {

	public ItCategory findByCategory(String category);

}
