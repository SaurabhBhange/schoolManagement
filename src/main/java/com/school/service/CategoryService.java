package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.Category;
import com.school.repository.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	Category category = null;

	public Category createCategory(Category c) {
		try {

			return categoryRepo.save(c);

		} catch (Exception e) {
			return null;
		}
	}

	public List<Category> getAllCategory() {
		return categoryRepo.findAll();
	}

	public Category getCategoryByName(String category) {
		try {
			return categoryRepo.findByCategory(category);

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void deleteCategory(int cat_id) {

		categoryRepo.deleteById(cat_id);
	}
}
