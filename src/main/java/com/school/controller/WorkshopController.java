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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.exception.RecordNotFoundException;
import com.school.model.Workshop;
import com.school.service.WorkshopService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class WorkshopController {

	@Autowired
	private WorkshopService service;

	@PostMapping("/workshop")
	public ResponseEntity<?> createExam(@RequestBody Workshop entity) throws IOException {
		Workshop exam = service.saveData(entity);
		if (exam != null) {
			return new ResponseEntity<Workshop>(exam, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccesful attempt", HttpStatus.NO_CONTENT);
		}
	}

	
	@GetMapping("/allData")
	public ResponseEntity<?> getAllCoursed() {
		List<Workshop> courses = service.getData();
		if (courses != null) {
			return new ResponseEntity<List<Workshop>>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}

	
	@GetMapping("/DataById/{workshopId}")
	public ResponseEntity<?> getCoursesById(@PathVariable int workshopId) {
		Workshop courses = service.getDataById(workshopId);
		if (courses != null) {
			return new ResponseEntity<Workshop>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}

	
	@PostMapping("/edit/workshop/{workshopId}")
	public ResponseEntity<?> update(@PathVariable int workshopId, @RequestParam String fullName,
			@RequestParam String email, @RequestParam String mobNumber, @RequestParam String courseName,
			@RequestParam String coursePrice, @RequestParam String RzrpOrderId, @RequestParam String RzrpSign,
			@RequestParam String PaymentStatus, @RequestParam String RzrpPaymentId) {
		if (fullName == null) {
			return new ResponseEntity<String>("Entities can not update", HttpStatus.OK);
		}
		Workshop w = service.updateData(workshopId, fullName, email, mobNumber, courseName, coursePrice, RzrpOrderId,
				RzrpPaymentId, RzrpSign, PaymentStatus);
		return new ResponseEntity<Workshop>(w, HttpStatus.OK);
	}

	
	@DeleteMapping("/delete/workshop/{workshopId}")
	public ResponseEntity<?> deleteCourse(@PathVariable int workshopId) {
		service.deleteData(workshopId);
		return new ResponseEntity<String>("Data deleted Successfully", HttpStatus.OK);
	}

}
