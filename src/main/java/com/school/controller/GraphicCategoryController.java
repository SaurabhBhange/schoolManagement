package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.school.model.GraphicCategory;
import com.school.service.GraphicCategoryService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class GraphicCategoryController {

	@Autowired
	@Qualifier("graphicCategoryService")
	GraphicCategoryService graphicCategoryService;

	// API For creating graphic category
	@PostMapping("/graphic/create")
	public GraphicCategory createCategory(@RequestBody GraphicCategory c) {
		return graphicCategoryService.createCategory(c);
	}

	// API For getting available categories
	@GetMapping("graphic/categories")
	public ResponseEntity<?> getAllCategory() {
		List<GraphicCategory> categories = graphicCategoryService.getAllCategory();
		if (categories != null) {
			return new ResponseEntity<List<GraphicCategory>>(categories, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No categories present in the DB to display", HttpStatus.NO_CONTENT);
		}
	}

	// API For deleting available graphic categories
	@DeleteMapping("/delete/graphic/{cat_id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int cat_id) {
		graphicCategoryService.deleteCategory(cat_id);
		return new ResponseEntity<String>("Category deleted successfully", HttpStatus.OK);
	}

}
