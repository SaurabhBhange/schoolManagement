package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.model.ItCategory;
import com.school.service.ItCategoryService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class ItCategoryController {

	@Autowired
	private ItCategoryService itCategoryService;

	// API For creating IT Category
	@PostMapping("/itcategory/create")
	public ResponseEntity<?> createCategory(@RequestBody ItCategory c) {
		if ("".equals(c.getCategory()) || c.getCategory() == null) {
			return new ResponseEntity<String>("category Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			ItCategory cat = itCategoryService.getCategoryByName(c.getCategory());
			if (cat != null) {
				return new ResponseEntity<String>("category already exist", HttpStatus.OK);
			} else {
				ItCategory category = itCategoryService.createCategory(c);
				if (category != null) {
					return new ResponseEntity<String>("category " + category.getCategory() + " created successfully",
							HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("unable to save category in DB",
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
	}

	// API For getting all avaialble IT Categories
	@GetMapping("/itcategories")
	public ResponseEntity<?> getAllCategory() {
		List<ItCategory> categories = itCategoryService.getAllCategory();
		if (categories != null) {
			return new ResponseEntity<List<ItCategory>>(categories, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No categories present in the DB to display", HttpStatus.NO_CONTENT);
		}
	}

	// API For Deleting IT categories
	@DeleteMapping("/delete/itcategory/{cat_id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int cat_id) {

		itCategoryService.deleteCategory(cat_id);

		return new ResponseEntity<String>("Category deleted successfully", HttpStatus.OK);

	}
}
