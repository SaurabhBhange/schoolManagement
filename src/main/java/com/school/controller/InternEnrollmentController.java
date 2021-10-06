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

import com.school.model.InternEnrollemnt;
import com.school.service.InternshipEnrollmentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class InternEnrollmentController {

	@Autowired
	private InternshipEnrollmentService trainingService;

	// API For creating Intern Entries
	@PostMapping("/intern/service")
	public ResponseEntity<?> createIneterest(@RequestBody InternEnrollemnt enroll) {
		System.out.println(enroll.getEmail() + " ");
		if (enroll.getEmail() == null || enroll.getEmail() == "") {
			String data = "fail";
			return new ResponseEntity<String>("{\"status\":\"" + data + "\"}", HttpStatus.OK);
		}

		InternEnrollemnt in = trainingService.createInterest(enroll);
		return new ResponseEntity<InternEnrollemnt>(in, HttpStatus.OK);

	}

	// API For getting available entries
	@GetMapping("intern/get/trainers")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam (defaultValue = "all")String data,
			@RequestParam (defaultValue = "all")String filterby) {

		Page<InternEnrollemnt> page = trainingService.getAllUsers(pageNo, pageSize,data,filterby);
		if (page != null) {
			return new ResponseEntity<UserPaginationDTO>(
					new UserPaginationDTO(page.getTotalElements(), page.getTotalPages(), page.getContent()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No services available", HttpStatus.NO_CONTENT);
		}
	}

	// API For updating comment and colour in intern training entries
	@PostMapping("/update/intern")
	public ResponseEntity<?> updateMethod(@RequestParam("id") int id, @RequestParam("comment") String comment,
			@RequestParam("colour") String colour) {
		int result = trainingService.updateInternEnrollment(colour, comment, id);
		if (result > 0) {
			return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Sorry Unable to Update", HttpStatus.OK);
		}
	}

	// API For deleting intern entries
	@DeleteMapping("/delete/intern/{id}")
	public ResponseEntity<?> deleteEnrollmentInterest(@PathVariable int id) {

		trainingService.deleteEnrollmentInterest(id);

		return new ResponseEntity<String>("EnrollmentInterest Successfully deleted", HttpStatus.OK);
	}

}
