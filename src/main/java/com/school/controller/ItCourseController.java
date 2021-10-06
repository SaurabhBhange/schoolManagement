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

import com.school.dto.ItMechCoursesDTO;
import com.school.exception.RecordNotFoundException;
import com.school.model.ItCourse;
import com.school.service.ItCourseService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class ItCourseController {

	@Autowired
	private ItCourseService itCourseService;

	// API For IT Course create
	@PostMapping("/itcourse/create")
	public ResponseEntity<?> createCourse(@RequestParam("courseImage") MultipartFile courseImage,
			@RequestParam("coursePDF") MultipartFile coursePDF, @RequestParam("courseName") String courseName,
			@RequestParam("basicPlan") String basicPlan, @RequestParam("premiumPlan") String premiumPlan,
			@RequestParam("category") String category) throws IOException {
		String FAIL = "unable to save data";
		if (courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			ItCourse c = itCourseService.getCourseByName(courseName);
			if (c != null) {
				return new ResponseEntity<String>("Course Already Exist", HttpStatus.OK);
			} else {
				ItCourse course = itCourseService.createCourse(courseImage, coursePDF, courseName, basicPlan,
						premiumPlan, category);

				if (course != null) {
					return new ResponseEntity<ItCourse>(course, HttpStatus.OK);
				} else {
					String data = "fail";
					return new ResponseEntity<String>("{\"status\":\"" + data + "\",\"desc\":\"" + FAIL + "\"}",
							HttpStatus.OK);
				}
			}
		}
	}

	// API For get all IT courses
	@GetMapping("/itallcourses")
	public ResponseEntity<?> getAllCourses() {
		List<ItCourse> courses = itCourseService.getAllCoursed();
		if (courses != null) {
			return new ResponseEntity<List<ItCourse>>(courses, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There are no courses to display", HttpStatus.NO_CONTENT);
		}
	}

	// API For update IT course
	@PutMapping("/itcourse/{course_id}")
	public ResponseEntity<?> updateCourse(@PathVariable int course_id, @RequestParam("category") String category,
			@RequestParam("courseImage") MultipartFile courseImage, @RequestParam("coursePDF") MultipartFile coursePDF,
			@RequestParam("courseName") String courseName, @RequestParam("basicPlan") String basicPlan,
			@RequestParam("premiumPlan") String premiumPlan) throws IOException {
		if (courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {

			ItCourse course = itCourseService.updateCourse(course_id, courseImage, coursePDF, courseName, basicPlan,
					premiumPlan, category);

			if (course != null) {
				return new ResponseEntity<String>("Course " + course.getCourseName() + " updated successfully",
						HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unable to save Course in DB", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	}

	// API For delete IT course
	@DeleteMapping("/itcourse/{course_id}")
	public ResponseEntity<?> deleteCourse(@PathVariable int course_id) {
		itCourseService.DeleteCourse(course_id);

		return new ResponseEntity<String>("Course deleted Successfully", HttpStatus.OK);

	}

	// API For get ITCourse according to category
	@GetMapping("/itcourses/{category}")
	public Set<ItCourse> getCoursesByCategory(@PathVariable String category) {

		Set<ItCourse> courses = new HashSet<ItCourse>();

		courses = itCourseService.getCoursesByCategory(category);

		return courses;
	}

	// API For get IT Course by category name
	@GetMapping("/getitcoursesByName/{category}")
	public Set<ItCourse> getItCoursesByCategory(@PathVariable String category) {
		Set<ItCourse> courses = new HashSet<ItCourse>();
		System.out.println("calling");
		courses = itCourseService.getCoursesByCategory(category);
		return courses;
	}

	// API For getting available IT,Mechanical courses
	@GetMapping("/itmechcourses")
	public ResponseEntity<?> getAllItMechCourses() {
		List<ItMechCoursesDTO> courses = itCourseService.getAllItMechCourses();
		if (courses != null) {
			return new ResponseEntity<List<ItMechCoursesDTO>>(courses, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There are no courses to display", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("itcourse/{course_id}")
	public ResponseEntity<?> getItCourseId(@PathVariable int course_id) {
		ItCourse courses = itCourseService.getById(course_id);
		if (courses != null) {
			return new ResponseEntity<ItCourse>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no course to display");
		}
	}

	@PutMapping("it/partialCourse/{course_id}")
	public ResponseEntity<?> updatePartialCourse(@PathVariable int course_id, @RequestParam("category") String category,
			@RequestParam("courseName") String courseName, @RequestParam("basicPlan") String basicPlan,
			@RequestParam("premiumPlan") String premiumPlan) throws IOException {
		if (courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			ItCourse course = itCourseService.updatePartialCourse(course_id, courseName, basicPlan, premiumPlan,
					category);

			if (course != null) {
				return new ResponseEntity<ItCourse>(course, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unable to save Course in DB", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

}
