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

import com.school.model.Designation;
import com.school.service.DesignationService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class DesignationController {

	@Autowired
	private DesignationService interestService;

	// API For creating Employment Designation
	@PostMapping("/create/designation")
	public Designation createInterest(@RequestBody Designation interest) {

		return interestService.createInterest(interest);
	}

// API For getting all availble Designations
	@GetMapping("/designations")
	public ResponseEntity<?> getAllNames() {
		List<Designation> names = interestService.getAllNames();

		if (names != null) {
			return new ResponseEntity<List<Designation>>(names, HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("No position present", HttpStatus.BAD_REQUEST);
		}

	}

// API For deleting designation which are Filled
	@DeleteMapping("/delete/designation/{designation_id}")
	public ResponseEntity<?> deleteInterest(@PathVariable int designation_id) {

		interestService.deleteInterest(designation_id);

		return new ResponseEntity<String>("position deleted successfully", HttpStatus.OK);
	}

}
