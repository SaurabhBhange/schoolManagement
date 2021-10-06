package com.school.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.school.model.Employment;
import com.school.service.EmploymentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class EmploymentController {

	@Autowired
	private EmploymentService hiringService;

	// API For Submitting Employment Form
	@PostMapping("/hiring")
	public ResponseEntity<?> create(String fullName, String email, String phoneNo, String message, MultipartFile resume,
			String designation) throws IOException {
		String FAIL = "uanble to save data";

		Employment e = hiringService.create(fullName, email, phoneNo, message, resume, designation);
		if (e != null) {
			return new ResponseEntity<Employment>(e, HttpStatus.OK);
		} else {
			String data = "fail";
			return new ResponseEntity<String>("{\"status\":\"" + data + "\",\"desc\":\"" + FAIL + "\"}", HttpStatus.OK);
		}
	}

	// API For getting available hiring forms
	@GetMapping("/forms")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Employment> page = hiringService.getAllUsers(pageNo, pageSize);
		if (page != null) {
			return new ResponseEntity<UserPaginationDTO>(
					new UserPaginationDTO(page.getTotalElements(), page.getContent(), page.getTotalPages()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No services available", HttpStatus.NO_CONTENT);
		}
	}

	// API For Deleting Employment Form
	@DeleteMapping("/delete/employmentform/{hiringId}")
	public ResponseEntity<?> deleteEmploymentForm(@PathVariable int hiringId) {
		hiringService.deleteEmploymentForm(hiringId);
		return new ResponseEntity<String>("Employment form successfully deleted", HttpStatus.OK);
	}
}
