package com.school.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.school.exception.RecordNotFoundException;
import com.school.model.GraphicCourse;
import com.school.model.InternshipCategory;
import com.school.model.InternshipCourse;
import com.school.service.InternshipService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class InternshipController {

	@Autowired
	private InternshipService categoryService;
	private Object String;

	// API For creating internship category create
	@PostMapping("internship/category/create")
	public ResponseEntity<?> createCategory(@RequestBody InternshipCategory c) {
		if ("".equals(c.getCategory()) || c.getCategory() == null) {
			return new ResponseEntity<String>("category Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			InternshipCategory cat = categoryService.getCategoryByName(c.getCategory());
			if (cat != null) {
				return new ResponseEntity<String>("category already exist", HttpStatus.OK);
			} else {
				InternshipCategory category = categoryService.createCategory(c);
				if (category != null) {
					return new ResponseEntity<InternshipCategory>(category, HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("unable to save category in DB",
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
	}

	// API For getting alal avaialble internship categories
	@GetMapping("/internship/categories")
	public ResponseEntity<?> getAllCategory() {
		List<InternshipCategory> categories = categoryService.getAllCategory();
		if (categories != null) {
			return new ResponseEntity<List<InternshipCategory>>(categories, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No categories present in the DB to display", HttpStatus.NO_CONTENT);
		}
	}

	// API For deleting available internship categories
	@DeleteMapping("/internship/delete/{category_id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int category_id) {

		categoryService.deleteCategory(category_id);

		return new ResponseEntity<String>("Category deleted successfully", HttpStatus.OK);
	}

	// API For creating Internship courses
	@PostMapping("internship/course/create")
	public ResponseEntity<?> createCourse(@RequestParam("courseImage") MultipartFile courseImage,
			@RequestParam("duration") String duration, @RequestParam("courseName") String courseName,
			@RequestParam("category") String category) throws IOException {
		String FAIL = "unable to save data in DB";
		if (courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			InternshipCourse c = categoryService.getCourseByName(courseName);
			if (c != null) {
				return new ResponseEntity<String>("Course Already Exist", HttpStatus.OK);
			} else {
				InternshipCourse course = categoryService.createCourse(courseImage, courseName, duration, category);

				if (course != null) {
					return new ResponseEntity<InternshipCourse>(course, HttpStatus.OK);

				} else
					return new ResponseEntity<String>("{\"status\":\"" + "\",\"desc\":\"" + FAIL + "\"}",
							HttpStatus.OK);
			}
		}
	}

	// API For get all internship courses
	@GetMapping("internship/allcourses")
	public ResponseEntity<?> getAllCoursed() {
		List<InternshipCourse> courses = categoryService.getAllCourses();

		if (courses != null) {
			return new ResponseEntity<List<InternshipCourse>>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}

	// API For get all courses with categories
	@GetMapping("internship/coursesCategories")
	public ResponseEntity<?> getAllCoursesCategories() {
		List<InternshipCourse> courses = categoryService.getAllCourses();

		if (courses != null) {
			return new ResponseEntity<List<InternshipCourse>>(courses, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There are no categories to display", HttpStatus.OK);
		}
	}

	// API For update Internship courses
	@PutMapping("/internship/{course_id}")
	public ResponseEntity<?> updateCourse(@PathVariable int course_id, @RequestParam("intern") String intern,
			@RequestParam("duration") String duration, @RequestParam("courseImage") MultipartFile courseImage,
			@RequestParam("courseName") String courseName) throws IOException {
		if (courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			InternshipCourse course = categoryService.updateCourse(course_id, courseImage, courseName, duration,
					intern);

			if (course != null) {
				return new ResponseEntity<String>("Course " + course.getCourseName() + " updated successfully",
						HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unable to save Course in DB", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	// API For deleting internship courses
	@DeleteMapping("internship/course/{course_id}")
	public ResponseEntity<?> deleteCourse(@PathVariable int course_id) {
		categoryService.DeleteCourse(course_id);

		return new ResponseEntity<String>("Course deleted Successfully", HttpStatus.OK);
	}

	// API For get course by category
	@GetMapping("internship/getcoursesByName")
	public Set<InternshipCourse> getCoursesByCategory(@RequestParam("category") String category) {
		Set<InternshipCourse> courses = new HashSet<InternshipCourse>();
		System.out.println("calling");
		courses = categoryService.getCoursesByCategory(category);
		return courses;
	}

	// API For get course by category
	@GetMapping("internship/getcoursesById/{cat_id}")
	public Set<InternshipCourse> getCoursesByCategoryId(@PathVariable int cat_id) {
		System.out.println("calling");
		Set<InternshipCourse> courses = new HashSet<InternshipCourse>();
		courses = categoryService.getCoursesByCategoryID(cat_id);
		return courses;
	}

	@GetMapping("internship/{course_id}")
	public ResponseEntity<?> getItCourseId(@PathVariable int course_id) {
		InternshipCourse courses = categoryService.getById(course_id);
		if (courses != null) {
			return new ResponseEntity<InternshipCourse>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no course to display");
		}
	}

	@PutMapping("internship/partialCourse/{course_id}")
	public ResponseEntity<?> updatePartialCourse(@PathVariable int course_id, @RequestParam("category") String category,
			@RequestParam("courseName") String courseName, @RequestParam("duration") String duration)
			throws IOException {
		if (courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			InternshipCourse course = categoryService.updatePartialCourse(course_id, courseName, duration, category);

			if (course != null) {
				return new ResponseEntity<InternshipCourse>(course, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unable to save Course in DB", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

}
