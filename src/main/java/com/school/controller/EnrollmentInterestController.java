package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.model.EnrollmentInterest;
import com.school.model.PaginationDto;
import com.school.service.EnrollmentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class EnrollmentInterestController {

	@Autowired
	private EnrollmentService trainingService;

	// API For creating Mechanical Training
	@PostMapping("/mechanical/service")
	public ResponseEntity<?> createIneterest(@RequestBody EnrollmentInterest enroll) {

		if (enroll.getEmail() == "") {
			String data = "fail";
			return new ResponseEntity<String>("{\"status\":\"" + data + "\"}", HttpStatus.OK);
		}

		EnrollmentInterest e = trainingService.createInterest(enroll);
		return new ResponseEntity<EnrollmentInterest>(e, HttpStatus.OK);

	}

	// API For getting Training forms
	@GetMapping("/get/trainers")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam (defaultValue = "all")String data,
			@RequestParam (defaultValue = "all")String filterby) {
		Page<EnrollmentInterest> page = trainingService.getAllUsers(pageNo, pageSize,data,filterby);
		if (page != null) {
			return new ResponseEntity<PaginationDto>(
					new PaginationDto(page.getTotalElements(), page.getContent(), page.getTotalPages()), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No lists are available in DB to display", HttpStatus.NO_CONTENT);
		}
	}

	// API For Updating comment and colour in training Forms
	@PostMapping("/update/training")
	public ResponseEntity<?> updateMethod(@RequestParam("id") int id, @RequestParam("comment") String comment,
			@RequestParam("colour") String colour) {
		int result = trainingService.updateItEnrollment(colour, comment, id);
		if (result > 0) {
			return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Sorry Unable to Update", HttpStatus.OK);
		}
	}

	

	// API For Deleting Training forms
	@DeleteMapping("/delete/enrollmentinterest/{id}")
	public ResponseEntity<?> deleteEnrollmentInterest(@PathVariable int id) {

		trainingService.deleteEnrollmentInterest(id);

		return new ResponseEntity<String>("EnrollmentInterest Successfully deleted", HttpStatus.OK);
	}

}
