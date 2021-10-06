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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.school.exception.RecordNotFoundException;
import com.school.model.Course;
import com.school.model.CourseBatchMapping;
import com.school.service.CourseService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class CourseController {

	@Autowired
	private CourseService courseService;

	// API For creating Mechanical Course
	@PostMapping("/course/create")
	public ResponseEntity<?> createCourse(@RequestParam("courseImage") MultipartFile courseImage,
			@RequestParam("coursePDF") MultipartFile coursePDF, @RequestParam("courseName") String courseName,
			@RequestParam("basicPlan") String basicPlan, @RequestParam("premiumPlan") String premiumPlan,
			@RequestParam("category") String category) throws IOException {
		String FAIL = "unable to save data in DB";
		if (courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			Course c = courseService.getCourseByName(courseName);
			if (c != null) {
				return new ResponseEntity<String>("Course Already Exist", HttpStatus.OK);
			} else {
				Course course = courseService.createCourse(courseImage, coursePDF, courseName, basicPlan, premiumPlan,
						category);

				if (course != null) {
					return new ResponseEntity<Course>(course, HttpStatus.OK);

				} else {
					String data = "fail";
					return new ResponseEntity<String>("{\"status\":\"" + data + "\",\"desc\":\"" + FAIL + "\"}",
							HttpStatus.OK);
				}
			}
		}
	}

	// API For get all Mechnanical courses
	@GetMapping("mechanical/allcourses")
	public ResponseEntity<?> getAllCoursed() {
		List<Course> courses = courseService.getAllCourses();
		if (courses != null) {
			return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}

	@GetMapping("mechanical/{course_id}")
	public ResponseEntity<?> getCourseId(@PathVariable int course_id) {
		Course courses = courseService.getById(course_id);
		if (courses != null) {
			return new ResponseEntity<Course>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no course to display");
		}
	}

	// API For get all courses with categories
	@GetMapping("/coursesCategories")
	public ResponseEntity<?> getAllCoursesCategories() {
		List<Course> courses = courseService.getAllCourses();
		if (courses != null) {
			return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There are no categories to display", HttpStatus.OK);
		}
	}

	// API For update Mechanical course
	@PutMapping("/course/{course_id}")
	public ResponseEntity<?> updateCourse(@PathVariable int course_id,
			@RequestParam(required = false, name = "category") String category,
			@RequestParam(required = false, name = "courseImage") MultipartFile courseImage,
			@RequestParam(required = false, name = "coursePDF") MultipartFile coursePDF,
			@RequestParam(required = false, name = "courseName") String courseName,
			@RequestParam(required = false, name = "basicPlan") String basicPlan,
			@RequestParam(required = false, name = "premiumPlan") String premiumPlan) throws IOException {
		if (courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			Course course = courseService.updateCourse(course_id, courseImage, coursePDF, courseName, basicPlan,
					premiumPlan, category);

			if (course != null) {
				return new ResponseEntity<String>("Course " + course.getCourseName() + " updated successfully",
						HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unable to save Course in DB", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@PutMapping("/partialCourse/{course_id}")
	public ResponseEntity<?> updatePartialCourse(@PathVariable int course_id, @RequestParam("category") String category,
			@RequestParam("courseName") String courseName, @RequestParam("basicPlan") String basicPlan,
			@RequestParam("premiumPlan") String premiumPlan) throws IOException {
		if (courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			Course course = courseService.updatePartialCourse(course_id, courseName, basicPlan, premiumPlan, category);

			if (course != null) {
				return new ResponseEntity<Course>(course, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unable to save Course in DB", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	// API For delete Mechanical course
	@DeleteMapping("/course/{course_id}")
	public ResponseEntity<?> deleteCourse(@PathVariable int course_id) {
		courseService.DeleteCourse(course_id);
		return new ResponseEntity<String>("Course deleted Successfully", HttpStatus.OK);
	}

	// API For get course by category Name
	@GetMapping("/getcoursesByName/{category}")
	public Set<Course> getCoursesByCategory(@PathVariable String category) {
		Set<Course> courses = new HashSet<Course>();
		System.out.println("calling");
		courses = courseService.getCoursesByCategory(category);
		return courses;
	}

	// API For getting IT and Mechanical courses at a same time
	@GetMapping("/entire/courses")
	public List<Course> findAllCourses() {
		return courseService.getAllCourses();
	}

	@GetMapping("/batch/{batch_id}")
	public List<CourseBatchMapping> fetchbyId(@PathVariable int batch_id) {
		return courseService.getData(batch_id);
	}
}