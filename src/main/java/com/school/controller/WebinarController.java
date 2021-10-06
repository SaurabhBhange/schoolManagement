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
import com.school.model.WebinarCategory;
import com.school.model.WebinarCourse;
import com.school.service.WebinarService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/")
public class WebinarController {

	@Autowired
	private WebinarService webinarService;

	// API For Webinar Category create
	@PostMapping("webinar/category/create")
	public ResponseEntity<?> createCategory(@RequestBody WebinarCategory c) {
		if ("".equals(c.getCategory()) || c.getCategory() == null) {
			return new ResponseEntity<String>("category Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			WebinarCategory category = webinarService.createCategory(c);
			if (category != null) {
				return new ResponseEntity<String>("category " + category.getCategory() + " created successfully",
						HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("unable to save category in DB", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	}

	// API For Getting all available Web Categories
	@GetMapping("webinar/categories")
	public ResponseEntity<?> getAllCategory() {
		List<WebinarCategory> categories = webinarService.getAllCategory();
		if (categories != null) {
			return new ResponseEntity<List<WebinarCategory>>(categories, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No categories present in the DB to display", HttpStatus.NO_CONTENT);
		}
	}

	// API For Deleting Webinar Categories
	@DeleteMapping("/delete/webinar/{cat_id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int cat_id) {

		webinarService.deleteCategory(cat_id);

		return new ResponseEntity<String>("Category deleted successfully", HttpStatus.OK);
	}

	// API For creating Webinar course
	@PostMapping("webinar/course/create")
	public ResponseEntity<?> createCourse(@RequestParam("courseImage") MultipartFile courseImage,
			@RequestParam("duration") String duration, @RequestParam("courseName") String courseName,
			@RequestParam("date") String date, @RequestParam("time") String time,
			@RequestParam("category") String category) throws IOException {
		String FAIL = "unable to save data in DB";
		if (courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			WebinarCourse c = webinarService.getCourseByName(courseName);
			if (c != null) {
				return new ResponseEntity<String>("Course Already Exist", HttpStatus.OK);
			} else {
				WebinarCourse course = webinarService.createCourse(courseImage, courseName, duration, date, time,
						category);

				if (course != null) {
					return new ResponseEntity<WebinarCourse>(course, HttpStatus.OK);

				} else
					return new ResponseEntity<String>("{\"status\":\"" + "\",\"desc\":\"" + FAIL + "\"}",
							HttpStatus.OK);
			}

		}
	}

	// API For Getting list of avaialble Web courses
	@GetMapping("webinar/allcourses")
	public ResponseEntity<?> getAllCoursed() {
		List<WebinarCourse> courses = webinarService.getAllCourses();

		if (courses != null) {
			return new ResponseEntity<List<WebinarCourse>>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}

	// API For get list of web courses with categories
	@GetMapping("webinar/coursesCategories")
	public ResponseEntity<?> getAllCoursesCategories() {
		List<WebinarCourse> courses = webinarService.getAllCourses();

		if (courses != null) {
			return new ResponseEntity<List<WebinarCourse>>(courses, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There are no categories to display", HttpStatus.OK);
		}
	}

	// API For update Webinar Courses
	@PutMapping("/webinar/{course_id}")
	public ResponseEntity<?> updateCourse(@PathVariable int course_id, @RequestParam("category") String category,
			@RequestParam("date") String date, @RequestParam("time") String time,
			@RequestParam("duration") String duration,
			@RequestParam(required = false, name = "courseImage") MultipartFile courseImage,
			@RequestParam("courseName") String courseName) throws IOException {
		if ("".equals(courseName) || courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			WebinarCourse course = webinarService.updateCourse(course_id, courseImage, courseName, duration, date, time,
					category);

			if (course != null) {
				return new ResponseEntity<String>("Course " + course.getCourseName() + " updated successfully",
						HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unable to save Course in DB", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	// API For delete Webinar course
	@DeleteMapping("webinar/course/{course_id}")
	public ResponseEntity<?> deleteCourse(@PathVariable int course_id) {
		webinarService.DeleteCourse(course_id);

		return new ResponseEntity<String>("Course deleted Successfully", HttpStatus.OK);
	}

	// API For get Webinar course by category
	@GetMapping("webinar/getcoursesByName/{category}")
	public Set<WebinarCourse> getCoursesByCategory(@PathVariable String category) {
		Set<WebinarCourse> courses = new HashSet<WebinarCourse>();
		System.out.println("calling");
		courses = webinarService.getCoursesByCategory(category);
		return courses;
	}

	@GetMapping("webinar/{course_id}")
	public ResponseEntity<?> getItCourseId(@PathVariable int course_id) {
		WebinarCourse courses = webinarService.getById(course_id);
		if (courses != null) {
			return new ResponseEntity<WebinarCourse>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no course to display");
		}
	}

	@PutMapping("webinar/partialCourse/{course_id}")
	public ResponseEntity<?> updatePartialCourse(@PathVariable int course_id, @RequestParam("category") String category,
			@RequestParam("courseName") String courseName, @RequestParam("date") String date,
			@RequestParam("duration") String duration, @RequestParam("time") String time) throws IOException {
		if (courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			WebinarCourse course = webinarService.updatePartialCourse(course_id, courseName, date, duration, time,
					category);

			if (course != null) {
				return new ResponseEntity<WebinarCourse>(course, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unable to save Course in DB", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

}
