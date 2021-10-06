package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

	public Category findByCategory(String category);

}
