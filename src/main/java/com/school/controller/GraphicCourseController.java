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
import com.school.model.GraphicCourse;
import com.school.service.GraphicCourseService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class GraphicCourseController {

	@Autowired
	GraphicCourseService courseService;

	// API For creating graphic course create
	@PostMapping("/graphic/make")
	public ResponseEntity<?> createGraphicCourse(@RequestParam("courseImage") MultipartFile courseImage,
			@RequestParam("coursePDF") MultipartFile coursePDF, @RequestParam("courseName") String courseName,
			@RequestParam("basicPlan") String basicPlan, @RequestParam("premiumPlan") String premiumPlan,
			@RequestParam("category") String category) throws IOException {
		String FAIL = "unable to save data in DB";
		if (courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			GraphicCourse c = courseService.getCourseByName(courseName);
			if (c != null) {
				return new ResponseEntity<String>("Course Already Exist", HttpStatus.OK);
			} else

			{
				GraphicCourse course = courseService.createCourse(courseImage, coursePDF, courseName, basicPlan,
						premiumPlan, category);

				if (course != null) {
					return new ResponseEntity<GraphicCourse>(course, HttpStatus.OK);
				} else {
					String data = "fail";
					return new ResponseEntity<String>("{\"status\":\"" + data + "\",\"desc\":\"" + FAIL + "\"}",
							HttpStatus.OK);
				}
			}
		}
	}

	// API For get all available courses
	@GetMapping("graphic/allcourses")
	public ResponseEntity<?> getAllCoursed() {
		List<GraphicCourse> courses = courseService.getAllCourses();

		if (courses != null) {
			return new ResponseEntity<List<GraphicCourse>>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}

	// API For delete course
	@DeleteMapping("/graphic/{course_id}")
	public ResponseEntity<?> deleteCourse(@PathVariable int course_id) {
		courseService.DeleteCourse(course_id);

		return new ResponseEntity<String>("Course deleted Successfully", HttpStatus.OK);
	}

	// API For update course
	@PutMapping("/graphic/{course_id}")
	public ResponseEntity<?> updateCourse(@PathVariable int course_id, @RequestParam("category") String category,
			@RequestParam("courseImage") MultipartFile courseImage, @RequestParam("coursePDF") MultipartFile coursePDF,
			@RequestParam("courseName") String courseName, @RequestParam("basicPlan") String basicPlan,
			@RequestParam("premiumPlan") String premiumPlan) throws IOException {
		if ("".equals(courseName) || courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			GraphicCourse course = courseService.updateCourse(course_id, courseImage, coursePDF, courseName, basicPlan,
					premiumPlan, category);

			if (course != null) {
				return new ResponseEntity<String>("Course " + course.getCourseName() + " updated successfully",
						HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unable to save Course in DB", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	// API For get course by category
	@GetMapping("/graphic/getcoursesByName/{category}")
	public Set<GraphicCourse> getCoursesByCategory(@PathVariable String category) {
		Set<GraphicCourse> courses = new HashSet<GraphicCourse>();
		System.out.println("calling");
		courses = courseService.getCoursesByCategory(category);
		return courses;
	}

	// API For get IT , Mechnical, Graphic course at a time
	@GetMapping("graphic/it/mech/courses")
	public ResponseEntity<?> getAllItMechCourses() {
		List<ItMechCoursesDTO> courses = courseService.getAllItMechGraphicCourses();
		if (courses != null) {
			return new ResponseEntity<List<ItMechCoursesDTO>>(courses, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There are no courses to display", HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("graphic/{course_id}")
	public ResponseEntity<?> getItCourseId(@PathVariable int course_id) {
		GraphicCourse courses = courseService.getById(course_id);
		if (courses != null) {
			return new ResponseEntity<GraphicCourse>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no course to display");
		}
	}
	
	@PutMapping("graphic/partialCourse/{course_id}")
	public ResponseEntity<?> updatePartialCourse(@PathVariable int course_id, @RequestParam("category") String category,
			@RequestParam("courseName") String courseName, @RequestParam("date") String date,
			@RequestParam("duration") String duration, @RequestParam("time") String time) throws IOException {
		if (courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			GraphicCourse course = courseService.updatePartialCourse(course_id, courseName, date, duration, time,
					category);

			if (course != null) {
				return new ResponseEntity<GraphicCourse>(course, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unable to save Course in DB", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
