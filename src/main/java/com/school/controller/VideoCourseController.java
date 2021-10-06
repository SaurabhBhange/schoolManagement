package com.school.controller;
import java.io.IOException;
import java.util.List;

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
import com.school.model.StudentPojo;
import com.school.model.VideoCourse;
import com.school.model.VideoUpload;
import com.school.model.uservideomapping;
import com.school.service.VideoCourseService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class VideoCourseController {

	@Autowired
	private VideoCourseService service;

	@PostMapping("/videocourse/create")
	public ResponseEntity<?> createCourse(@RequestParam("courseImage") MultipartFile courseImage,
			@RequestParam("coursePDF") MultipartFile coursePDF, @RequestParam("courseName") String courseName,
			@RequestParam("price") String price) throws IOException {
		String FAIL = "unable to save data in DB";
		if ("".equals(courseName) || courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			// VideoCourse c = service.getCourseByName(courseName);
			// if (c != null) {
//				return new ResponseEntity<String>("Course Already Exist", HttpStatus.OK);
			// } else {
			VideoCourse course = service.createCourse(courseImage, coursePDF, courseName, price);

			if (course != null) {
				return new ResponseEntity<VideoCourse>(course, HttpStatus.OK);

			} else {
				String data = "fail";
				return new ResponseEntity<String>("{\"status\":\"" + data + "\",\"desc\":\"" + FAIL + "\"}",
						HttpStatus.OK);
			}
		}

	}

	// API For get all Mechnanical courses
	@GetMapping("video/allcourses")
	public ResponseEntity<?> getAllCoursed() {
		List<VideoCourse> courses = service.getAllCourses();

		if (courses != null) {
			return new ResponseEntity<List<VideoCourse>>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}

	// API For update Mechanical course
	@PutMapping("/videocourse/{videoCourseId}")
	public ResponseEntity<?> updateCourse(@PathVariable int videoCourseId,
			@RequestParam("courseImage") MultipartFile courseImage, @RequestParam("coursePDF") MultipartFile coursePDF,
			@RequestParam("courseName") String courseName, @RequestParam("price") String price) throws IOException {
		if ("".equals(courseName) || courseName == null) {
			return new ResponseEntity<String>("course Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			VideoCourse course = service.updateCourse(videoCourseId, courseImage, coursePDF, courseName, price);

			if (course != null) {
				return new ResponseEntity<String>("Course " + course.getCourseName() + " updated successfully",
						HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unable to save Course in DB", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	// API For delete Mechanical course
	@DeleteMapping("/videocourse/{videoCourseId}")
	public ResponseEntity<?> deleteCourse(@PathVariable int videoCourseId) {
		service.DeleteCourse(videoCourseId);
		return new ResponseEntity<String>("Course deleted Successfully", HttpStatus.OK);
	}

	@PostMapping("/subscribe/{videoCourseId}/{userId}")
	public ResponseEntity<?> subscribe(@PathVariable("videoCourseId") VideoCourse videoCourseId,
			@PathVariable("userId") StudentPojo  userId) {
		uservideomapping us = service.Subscribe(videoCourseId, userId);
		if (us != null) {
			return new ResponseEntity<uservideomapping>(us,HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unable to Subscribe Course", HttpStatus.OK);
		}
	}

	@DeleteMapping("/deletesubscription/{id}")
	public ResponseEntity<?> subscribe(@RequestParam("id") int id) {
		service.DeleteSubscription(id);
		return new ResponseEntity<String>("Subscription Deleted Successfully", HttpStatus.OK);
	}
	
	@GetMapping("/videolist/{user_id}")
	public ResponseEntity<?> FetchData(@PathVariable int user_id) {
		List<VideoUpload> uvm = service.getData(user_id);
		
		if(uvm != null) {
			return new ResponseEntity<List<VideoUpload>>(uvm,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Unable to Subscribe Course", HttpStatus.OK);
		}
	}
}
