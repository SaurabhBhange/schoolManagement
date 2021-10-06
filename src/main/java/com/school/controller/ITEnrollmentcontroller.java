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

import com.school.model.ITEnrollmentInterest;
import com.school.model.PaginationDto;
import com.school.service.ITEnrollmentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class ITEnrollmentcontroller {

	@Autowired
	private ITEnrollmentService ittrainingService;

	// API For creating IT Trainig Lead
	@PostMapping("/create/ittraining")
	public ResponseEntity<?> createIneterest(@RequestBody ITEnrollmentInterest enroll) {
		if (enroll.getEmail() == "") {
			String data = "fail";
			return new ResponseEntity<String>("{\"status\":\"" + data + "\"}", HttpStatus.OK);
		}
		ITEnrollmentInterest it = ittrainingService.createInterest(enroll);
		return new ResponseEntity<ITEnrollmentInterest>(it, HttpStatus.OK);

	}

	// API For available getting IT Trainig Lead
	@GetMapping("/get/ittrainers")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "all") String data,
			@RequestParam(defaultValue = "all") String filterby) {
		Page<ITEnrollmentInterest> page = ittrainingService.getAllUsers(pageNo, pageSize, data, filterby);
		if (page != null) {
			return new ResponseEntity<PaginationDto>(
					new PaginationDto(page.getContent(), page.getTotalElements(), page.getTotalPages()), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No services available", HttpStatus.NO_CONTENT);
		}
	}

	// API For Update IT Trainig Lead
	@PostMapping("/update/itenrollment")
	public ResponseEntity<?> updateMethod(@RequestParam("id") int id, @RequestParam("comment") String comment,
			@RequestParam("colour") String colour) {

		int result = ittrainingService.updateItEnrollment(colour, comment, id);

		if (result > 0) {
			return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Sorry Unable to Update", HttpStatus.OK);
		}
	}

	// API For Deleting IT Trainig Lead
	@DeleteMapping("/delete/itenrollmentinterest/{id}")
	public ResponseEntity<?> deleteEnrollmentInterest(@PathVariable int id) {

		ittrainingService.deleteEnrollmentInterest(id);

		return new ResponseEntity<String>("EnrollmentInterest Successfully deleted", HttpStatus.OK);
	}

}
