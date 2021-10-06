package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.ItCategory;
import com.school.repository.ItCategoryRepo;

@Service
public class ItCategoryService {

	@Autowired
	private ItCategoryRepo itCategoryRepo;
	ItCategory category = null;

	public ItCategory createCategory(ItCategory c) {
		try {

			return itCategoryRepo.save(c);

		} catch (Exception e) {
			return null;
		}
	}

	public List<ItCategory> getAllCategory() {
		return itCategoryRepo.findAll();
	}

	public ItCategory getCategoryByName(String category) {
		try {
			return itCategoryRepo.findByCategory(category);

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void deleteCategory(int cat_id) {

		itCategoryRepo.deleteById(cat_id);
	}
}
